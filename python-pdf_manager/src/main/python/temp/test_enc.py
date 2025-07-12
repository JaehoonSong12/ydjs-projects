"""
app.py

A minimal Flask application starter script following PEP8 and PEP257 conventions.

This script defines:
- A root route ('/') returning a simple greeting.
- An API endpoint ('/api/data') accepting JSON POST and echoing payload.

Requirements:
- Flask installed (`pip install Flask`).
"""
from flask import Flask, request, jsonify

app = Flask(__name__)
app.config.from_mapping(
    DEBUG=True,
    JSON_SORT_KEYS=False  # Maintain insertion order of JSON responses
)

@app.route('/', methods=['GET'])
def index() -> str:
    """
    Root endpoint.

    Returns:
        A simple greeting string.
    """
    return 'Hello, Flask!'

@app.route('/api/data', methods=['POST'])
def data_api() -> 'flask.wrappers.Response':
    """
    JSON API endpoint that echoes incoming payload.

    Expects:
        JSON body in the request.

    Returns:
        JSON response containing the received payload.
    """
    payload = request.get_json(force=True)
    response = {'received': payload}
    return jsonify(response)

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
