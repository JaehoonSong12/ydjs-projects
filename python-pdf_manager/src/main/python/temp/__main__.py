# temp/__main__.py
"""
entry-point module
__main__.py: Python will run alpaca_tradebot/__main__.py when you do `python -m alpaca_tradebot`.
"""

from .pygame_test import main
from .test_enc import app




# """
# run.py

# Entry point to start the server without external frameworks.
# """
# from config import config
# from mockapp import create_app
# from mockapp.server import run_simple


# class BaseConfig:
#     HOST = '0.0.0.0'
#     PORT = 5000
#     DEBUG = True

# config = BaseConfig

# def main():
#     app = create_app()
#     run_simple(app, host=config.HOST, port=config.PORT)



if __name__ == "__main__":
    main()
    # app.run(host='0.0.0.0', port=5000)



