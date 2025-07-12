"""
mockapp/__init__.py

Creates and configures the WSGI application.
"""
from .router import Router
from .utils import json_response, text_response


def create_app():
    """
    Application factory.

    Returns:
        WSGI callable implementing the application.
    """
    router = Router()

    # Register routes
    router.add_route('GET', '/', lambda env, start: text_response('Hello, MockApp!'))
    router.add_route('POST', '/api/data', lambda env, start: json_response({'received': env['json']}))

    return router.wsgi_app