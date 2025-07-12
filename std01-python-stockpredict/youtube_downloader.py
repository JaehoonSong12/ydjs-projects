###### source ../venv/Scripts/activate
###### python std01-jayden-stockpredict/youtube_downloader.py

# youtube_downloader.py
#  Need to install:
#         pip uninstall youtube-dl
#         pip install yt-dlp














import yt_dlp
import os

# ───────────────────────────────────────────────────────────────────────────────
# CONFIGURATION
# ───────────────────────────────────────────────────────────────────────────────

YOUTUBE_URL = "https://www.youtube.com/watch?v=0PmvcOSPI6A"
OUTPUT_DIR  = "./downloads"
VIDEO_FORMAT_LOG = "%(title).100s.%(ext)s"  # filename template for video
AUDIO_FORMAT_LOG = "%(title).100s.%(ext)s"  # filename template for audio

os.makedirs(OUTPUT_DIR, exist_ok=True)


# ───────────────────────────────────────────────────────────────────────────────
# FUNCTION TO DOWNLOAD BEST MP4
# ───────────────────────────────────────────────────────────────────────────────
def download_best_mp4(youtube_url: str, output_dir: str):
    """
    Uses yt_dlp to download the best‐quality MP4 stream available.
    """
    ydl_opts = {
        # “bestvideo[ext=mp4]+bestaudio[ext=m4a]/best[ext=mp4]” ensures:
        #   1) If there's a separate “video‐only” + “audio‐only” stream in MP4 format,
        #      it merges them (muxer needs ffmpeg/avconv installed).
        #   2) Otherwise fallback to the best single‐file MP4.
        "format": "bestvideo[ext=mp4]+bestaudio[ext=m4a]/best[ext=mp4]/best",
        "outtmpl": os.path.join(output_dir, VIDEO_FORMAT_LOG),
        "merge_output_format": "mp4",   # ensure that if separate, they’re muxed into .mp4
        "writesubtitles": False,        # set True if you want subtitles
        "noplaylist": True,             # if URL is a playlist, only download 1st video
        "quiet": True,                  # set to False if you want verbose logs
        "no_warnings": True,
    }

    with yt_dlp.YoutubeDL(ydl_opts) as ydl:
        info_dict = ydl.extract_info(youtube_url, download=True)
        # info_dict contains metadata (title, ext, etc.)
        filename = ydl.prepare_filename(info_dict)
        return filename


# ───────────────────────────────────────────────────────────────────────────────
# FUNCTION TO DOWNLOAD BEST MP3
# ───────────────────────────────────────────────────────────────────────────────
def download_best_mp3(youtube_url: str, output_dir: str):
    """
    Uses yt_dlp to download the best‐quality audio‐only stream (re‐encoded to MP3).
    """
    ydl_opts = {
        # “bestaudio/best” picks the highest‐bitrate audio. Then “postprocessors”
        # re‐encode it to MP3 at 192kbps (adjustable in “preferredquality”).
        "format": "bestaudio/best",
        "outtmpl": os.path.join(output_dir, AUDIO_FORMAT_LOG),
        "postprocessors": [{
            "key": "FFmpegExtractAudio",
            "preferredcodec": "mp3",
            "preferredquality": "192",
        }],
        "quiet": True,
        "no_warnings": True,
        "noplaylist": True,
    }

    with yt_dlp.YoutubeDL(ydl_opts) as ydl:
        info_dict = ydl.extract_info(youtube_url, download=True)
        # The resulting filename will end in “.mp3” (because of the postprocessor).
        title = info_dict.get("title", "").strip()
        ext   = "mp3"
        final_name = ydl.prepare_filename(info_dict)
        # yt_dlp’s “prepare_filename” still gives the original extension (e.g. .webm or .m4a).
        # To get the actual “.mp3” path, replace the extension:
        base, _ = os.path.splitext(final_name)
        return base + "." + ext


# ───────────────────────────────────────────────────────────────────────────────
# MAIN BLOCK
# ───────────────────────────────────────────────────────────────────────────────
if __name__ == "__main__":
    print("Downloading best MP4 …")
    video_path = download_best_mp4(YOUTUBE_URL, OUTPUT_DIR)
    print(f"  → Saved MP4 to: {video_path}")

    print("Downloading best MP3 …")
    audio_path = download_best_mp3(YOUTUBE_URL, OUTPUT_DIR)
    print(f"  → Saved MP3 to: {audio_path}")

    print("\nFinished downloading both MP4 and MP3.")














# "https://www.youtube.com/watch?v=0PmvcOSPI6A"

# https://www.ffmpeg.org/download.html




# https://www.gyan.dev/ffmpeg/builds/ffmpeg-release-essentials.zip#     print("\nFinished downloading both MP4 and MP3.")


# "https://www.youtube.com/watch?v=0PmvcOSPI6A"

# https://www.ffmpeg.org/download.html



















# from pytube import YouTube

# # Replace with your desired YouTube URL
# url = "https://www.youtube.com/watch?v=0PmvcOSPI6A"

# # Create YouTube object
# yt = YouTube(url)

# # Get the highest resolution stream
# stream = yt.streams.get_highest_resolution()

# # Download the video
# stream.download(output_path="downloads", filename="video.mp4")

# print("Download complete!")