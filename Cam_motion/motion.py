


# motion.py
import cv2 as cv
import time
import os
import subprocess
import platform
import threading
from pathlib import Path
import math
import shutil

# try importing ctypes for some platform calls
import ctypes

# --- CONFIG ---
PDF_REL_PATH = Path(__file__).parent / "study2.pdf"
FALLBACK_PNG = Path(__file__).parent / "study_page1.png"
WARMUP_SECONDS = 2.0
MOTION_AREA_THRESH = 700
NEW_OBJECT_DIST_PX = 60
TRACK_EXPIRY_SECONDS = 2.0
CAPTURE_INDICES = (0, 1, 2)
CAPTURE_BACKENDS = (cv.CAP_DSHOW, cv.CAP_MSMF, 0)
PDF_WINDOW_NAME = "Study PDF"
# ----------------

def pdf_to_cv_image(pdf_path: Path):
    try:
        import fitz  # PyMuPDF
    except Exception:
        print("PyMuPDF not installed (pip install pymupdf). Skipping PDF render.")
        return None

    try:
        doc = fitz.open(str(pdf_path))
        page = doc.load_page(0)
        mat = fitz.Matrix(2.0, 2.0)
        pix = page.get_pixmap(matrix=mat, alpha=False)
        import numpy as np
        arr = np.frombuffer(pix.tobytes(), dtype=np.uint8)
        arr = arr.reshape((pix.height, pix.width, 3))
        arr = arr[:, :, ::-1].copy()  # RGB -> BGR
        doc.close()
        return arr
    except Exception as e:
        print("Failed to render PDF to image:", e)
        return None

def bring_window_to_front(window_name: str):
    if platform.system() != "Windows":
        return
    try:
        FindWindowW = ctypes.windll.user32.FindWindowW
        SetForegroundWindow = ctypes.windll.user32.SetForegroundWindow
        hwnd = FindWindowW(None, window_name)
        if hwnd:
            SetForegroundWindow(hwnd)
    except Exception:
        pass

def try_open_camera(indices=CAPTURE_INDICES, backends=CAPTURE_BACKENDS):
    for idx in indices:
        for backend in backends:
            print(f"Trying camera idx={idx} backend={backend} ...", end=" ")
            cap = cv.VideoCapture(idx, backend)
            time.sleep(0.3)
            if cap.isOpened():
                ret, _ = cap.read()
                if ret:
                    print("OK")
                    return cap, idx, backend
                cap.release()
            print("failed")
    return None, None, None

def dist(a, b):
    return math.hypot(a[0]-b[0], a[1]-b[1])

# --- AUDIO: prefer pycaw on Windows and target the current/default output device ---
def set_system_volume_zero():
    """
    Set master volume to 0 on the current/default output device (Windows preferred).
    Uses pycaw if available (robust: EndpointVolume or Activate paths), else falls back.
    """
    system = platform.system()
    try:
        if system == "Windows":
            try:
                # try pycaw first
                from ctypes import POINTER, cast
                from comtypes import CLSCTX_ALL
                from pycaw.pycaw import AudioUtilities, IAudioEndpointVolume

                device = AudioUtilities.GetSpeakers()  # default output device
                # Debug: print friendly name if present
                try:
                    print("[pycaw] Output device:", getattr(device, "FriendlyName", "(unknown)"))
                except Exception:
                    pass

                # Preferred: use EndpointVolume if exposed
                try:
                    ev = device.EndpointVolume  # often already the endpoint interface
                    # try calling SetMasterVolumeLevelScalar directly
                    try:
                        ev.SetMasterVolumeLevelScalar(0.0, None)
                        try:
                            ev.SetMute(1, None)
                        except Exception:
                            pass
                        print("[pycaw] Used device.EndpointVolume -> muted")
                        return True
                    except Exception:
                        # if that's not an endpoint interface, try QueryInterface
                        try:
                            vol = ev.QueryInterface(IAudioEndpointVolume)
                            vol.SetMasterVolumeLevelScalar(0.0, None)
                            try:
                                vol.SetMute(1, None)
                            except Exception:
                                pass
                            print("[pycaw] Used EndpointVolume.QueryInterface -> muted")
                            return True
                        except Exception:
                            pass
                except Exception:
                    # no EndpointVolume attribute; try Activate path
                    pass

                # Fallback: Activate the IAudioEndpointVolume interface (older examples)
                try:
                    interface = device.Activate(IAudioEndpointVolume._iid_, CLSCTX_ALL, None)
                    volume = cast(interface, POINTER(IAudioEndpointVolume))
                    volume.SetMasterVolumeLevelScalar(0.0, None)
                    try:
                        volume.SetMute(1, None)
                    except Exception:
                        pass
                    print("[pycaw] Used device.Activate -> muted")
                    return True
                except Exception as e:
                    print("[pycaw] Activate fallback failed:", e)
                    # try legacy fallback
            except Exception as e:
                print("[pycaw] import/usage failed:", e)

            # Legacy fallback (winmm) if pycaw unavailable/failed
            try:
                ctypes.windll.winmm.waveOutSetVolume(0, 0)
                print("[winmm] waveOutSetVolume -> muted")
                return True
            except Exception as e:
                print("[winmm] fallback failed:", e)
                return False

        elif system == "Darwin":
            try:
                subprocess.call(["osascript", "-e", "set volume output volume 0"])
                return True
            except Exception as e:
                print("macOS osascript failed:", e)
                return False

        else:  # Linux
            # (keep your existing pulsectl/pactl fallbacks)
            # ... code omitted here for brevity in the snippet ...
            # return True/False appropriately
            pass

    except Exception as e:
        print("Volume mute failed (outer):", e)
        return False

def on_new_moving_object(pdf_img):
    """Called when a new moving object is detected."""
    muted = set_system_volume_zero()
    if muted:
        print("System volume set to 0 (muted).")
    else:
        print("Failed to mute system volume (best-effort).")

    # show the pre-rendered PDF image instantly (fast)
    if pdf_img is not None:
        cv.imshow(PDF_WINDOW_NAME, pdf_img)
        bring_window_to_front(PDF_WINDOW_NAME)
    else:
        # fallback: open actual PDF in default viewer (slow)
        if PDF_REL_PATH.exists():
            print("Opening external PDF viewer (fallback).")
            threading.Thread(target=lambda: os.startfile(str(PDF_REL_PATH)) if platform.system()=="Windows"
                             else subprocess.Popen(["open", str(PDF_REL_PATH)]) if platform.system()=="Darwin"
                             else subprocess.Popen(["xdg-open", str(PDF_REL_PATH)]),
                             daemon=True).start()
        elif FALLBACK_PNG.exists():
            img = cv.imread(str(FALLBACK_PNG))
            if img is not None:
                cv.imshow(PDF_WINDOW_NAME, img)
                bring_window_to_front(PDF_WINDOW_NAME)
        else:
            print("No PDF or fallback image available to display.")

def motion_loop(cap, pdf_img):
    backSub = cv.createBackgroundSubtractorMOG2(history=500, varThreshold=16, detectShadows=True)
    tracked = []
    start_time = time.time()

    cv.namedWindow(PDF_WINDOW_NAME, cv.WINDOW_NORMAL)
    if pdf_img is not None:
        h, w = pdf_img.shape[:2]
        cv.resizeWindow(PDF_WINDOW_NAME, min(w, 1200), min(h, 800))

    print(f"Warmup for {WARMUP_SECONDS} seconds...")
    while True:
        ret, frame = cap.read()
        if not ret or frame is None:
            print("Frame read failed — attempting reconnect...")
            time.sleep(1)
            cap, _, _ = try_open_camera()
            if cap is None:
                print("Reconnect failed; retrying...")
                time.sleep(2)
                continue
            continue

        now = time.time()
        fgmask = backSub.apply(frame)
        k = cv.getStructuringElement(cv.MORPH_ELLIPSE, (3,3))
        fgmask = cv.morphologyEx(fgmask, cv.MORPH_OPEN, k, iterations=1)
        fgmask = cv.dilate(fgmask, None, iterations=2)

        contours, _ = cv.findContours(fgmask, cv.RETR_EXTERNAL, cv.CHAIN_APPROX_SIMPLE)
        new_centroids = []
        for cnt in contours:
            if cv.contourArea(cnt) < MOTION_AREA_THRESH:
                continue
            x,y,w,h = cv.boundingRect(cnt)
            cx = x + w//2
            cy = y + h//2
            new_centroids.append((cx, cy))
            cv.rectangle(frame, (x,y), (x+w,y+h), (0,255,0), 2)

        tracked = [(tx,ty,t_last) for (tx,ty,t_last) in tracked if (now - t_last) <= TRACK_EXPIRY_SECONDS]

        if (now - start_time) >= WARMUP_SECONDS:
            for c in new_centroids:
                is_new = True
                for i, (tx,ty,t_last) in enumerate(tracked):
                    if dist(c, (tx,ty)) <= NEW_OBJECT_DIST_PX:
                        tracked[i] = (tx,ty, now)
                        is_new = False
                        break
                if is_new:
                    tracked.append((c[0], c[1], now))
                    print(f"[{time.strftime('%H:%M:%S')}] New moving object at {c} -> trigger")
                    on_new_moving_object(pdf_img)

        for (cx,cy) in new_centroids:
            cv.circle(frame, (cx,cy), 4, (255,0,0), -1)

        cv.imshow("Motion Capture", frame)
        cv.imshow("Mask", fgmask)

        key = cv.waitKey(30) & 0xFF
        if key == ord('q'):
            break
        if key == ord('r'):
            print("User requested camera reopen")
            cap.release()
            cap, _, _ = try_open_camera()
            if cap is None:
                print("Reopen failed")

    cap.release()
    cv.destroyAllWindows()

if __name__ == "__main__":
    pdf_img = None
    if PDF_REL_PATH.exists():
        print("Rendering PDF to image (startup) — requires PyMuPDF (pymupdf).")
        pdf_img = pdf_to_cv_image(PDF_REL_PATH)
        if pdf_img is None:
            print("PDF render failed or PyMuPDF missing. If you have a pre-made PNG, place 'study_page1.png' next to script.")
    else:
        if FALLBACK_PNG.exists():
            pdf_img = cv.imread(str(FALLBACK_PNG))
            print("Using fallback PNG image.")
        else:
            print("No study.pdf or fallback PNG found. External viewer will be used as fallback (slower).")

    cap, idx, backend = try_open_camera()
    if cap is None:
        print("No camera. Trying video file fallback 'test.mp4' ...")
        cap = cv.VideoCapture("test.mp4")
        if not cap.isOpened():
            raise SystemExit("Couldn't open camera or fallback video. Check device and file paths.")
    print("Starting. Press 'q' to quit.")
    motion_loop(cap, pdf_img)
