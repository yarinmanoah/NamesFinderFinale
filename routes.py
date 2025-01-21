from name_data import names_blueprint

def initial_routes(app):
    app.register_blueprint(names_blueprint)