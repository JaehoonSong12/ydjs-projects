import numpy as np
import matplotlib.pyplot as plt
from matplotlib.animation import FuncAnimation, FFMpegWriter

def g(x):
    return x**2 + 4

def f(u):
    return np.sqrt(np.maximum(u, 0))

# Domain
x_vals = np.linspace(-8, 8, 200)
g_vals = g(x_vals)
u_vals = np.linspace(min(g_vals), max(g_vals), 400)
f_vals = f(u_vals)

# Figure with 2 panels
fig, axs = plt.subplots(1, 2, figsize=(12, 4))

# LEFT — x → g(x)
axs[0].plot(x_vals, g_vals, color="blue", label="g(x) = x² + 4")
axs[0].set_title("Step 1:  x → g(x)")
axs[0].set_xlim(-20, 20)   # wider domain
axs[0].set_ylim(-10, 20)  # taller codomain for x² + 4

axs[0].legend()

px1, = axs[0].plot([], [], 'ro', markersize=8)
pg1, = axs[0].plot([], [], 'bo', markersize=8)
line1, = axs[0].plot([], [], 'k--')

# RIGHT — g(x) → f(g(x))
axs[1].plot(u_vals, f_vals, color="green", label="f(u) = √u")
axs[1].set_title("Step 2:  g(x) → f(g(x))")
axs[1].set_xlim(0, max(g_vals) + 5)   # more space on left & right
axs[1].set_ylim(0, np.sqrt(max(g_vals)) + 3)  # higher codomain

axs[1].legend()

pg2, = axs[1].plot([], [], 'bo', markersize=8)
pf2, = axs[1].plot([], [], 'go', markersize=8)
line2, = axs[1].plot([], [], 'k--')

# Animation update
def update(frame):
    x = x_vals[frame]
    y = g(x)
    z = f(y)

    # Left panel markers
    px1.set_data([x], [0])
    pg1.set_data([x], [y])
    line1.set_data([x, x], [0, y])

    # Right panel markers
    pg2.set_data([y], [0])
    pf2.set_data([y], [z])
    line2.set_data([y, y], [0, z])

    return px1, pg1, line1, pg2, pf2, line2

ani = FuncAnimation(fig, update, frames=len(x_vals), interval=40)

writer = FFMpegWriter(fps=30)
ani.save("two_step_composition.mp4", writer=writer)

print("Saved two_step_composition.mp4")
