from flask import Blueprint, request, jsonify
from mongodb_connection_manager import MongoConnectionManager
import uuid
import random

# Initialize Blueprint
names_blueprint = Blueprint('Names', __name__)

# 1. List All Names
@names_blueprint.route('/list_all_names', methods=['GET'])
def list_all_names():
    """
    Retrieve all names from the database
    ---
    responses:
        200:
            description: A list of all names
        500:
            description: An error occurred while fetching names
    """
    db = MongoConnectionManager.get_db()
    names_collection = db['names']
    try:
        names = list(names_collection.find())
        for name in names:
            name['_id'] = str(name['_id'])
        return jsonify(names), 200
    except Exception as e:
        return jsonify({"error": str(e)}), 500

# 2. List by Letter
@names_blueprint.route('/list_by_letter/<char>', methods=['GET'])
def list_by_letter(char):
    """
    Retrieve names that start with a specific letter
    ---
    parameters:
      - name: char
        in: path
        required: true
        type: string
        description: The starting letter of the names
    responses:
        200:
            description: A list of names starting with the specified letter
        404:
            description: No names found
        500:
            description: An error occurred
    """
    db = MongoConnectionManager.get_db()
    names_collection = db['names']
    try:
        names = list(names_collection.find({"FirstChar": char}))
        for name in names:
            name['_id'] = str(name['_id'])
        if names:
            return jsonify(names), 200
        return jsonify({"error": "No names found"}), 404
    except Exception as e:
        return jsonify({"error": str(e)}), 500

# 3. List by Category
@names_blueprint.route('/list_by_category/<category>', methods=['GET'])
def list_by_category(category):
    """
    Retrieve names by category
    ---
    parameters:
      - name: category
        in: path
        required: true
        type: string
        description: The category of the names
    responses:
        200:
            description: A list of names in the specified category
        404:
            description: No names found
        500:
            description: An error occurred
    """
    db = MongoConnectionManager.get_db()
    names_collection = db['names']
    try:
        names = list(names_collection.find({"category": category}))
        for name in names:
            name['_id'] = str(name['_id'])
        if names:
            return jsonify(names), 200
        return jsonify({"error": "No names found"}), 404
    except Exception as e:
        return jsonify({"error": str(e)}), 500

# 4. List by Letter and Category
@names_blueprint.route('/list_by_letter_and_category', methods=['GET'])
def list_by_letter_and_category():
    """
    Retrieve names by letter and category
    ---
    parameters:
      - name: letter
        in: query
        required: true
        type: string
        description: The starting letter of the names
      - name: category
        in: query
        required: true
        type: string
        description: The category of the names
    responses:
        200:
            description: A list of names matching the letter and category
        404:
            description: No names found
        500:
            description: An error occurred
    """
    db = MongoConnectionManager.get_db()
    names_collection = db['names']
    letter = request.args.get('letter')
    category = request.args.get('category')
    try:
        names = list(names_collection.find({"FirstChar": letter, "category": category}))
        for name in names:
            name['_id'] = str(name['_id'])
        if names:
            return jsonify(names), 200
        return jsonify({"error": "No names found"}), 404
    except Exception as e:
        return jsonify({"error": str(e)}), 500

# 5. List Random Name
@names_blueprint.route('/list_random_name', methods=['GET'])
def list_random_name():
    """
    Retrieve a random name from the database
    ---
    responses:
        200:
            description: A random name
        500:
            description: An error occurred
    """
    db = MongoConnectionManager.get_db()
    names_collection = db['names']
    try:
        names = list(names_collection.find())
        if names:
            random_name = random.choice(names)
            random_name['_id'] = str(random_name['_id'])
            return jsonify(random_name), 200
        return jsonify({"error": "No names found"}), 404
    except Exception as e:
        return jsonify({"error": str(e)}), 500

# 6. Add Name
@names_blueprint.route('/add_name', methods=['POST'])
def add_name():
    """
    Add a new name to the database
    ---
    parameters:
      - name: body
        in: body
        required: true
        schema:
            type: object
            properties:
                FirstChar:
                    type: string
                content:
                    type: string
                category:
                    type: string
                id:
                    type: integer
    responses:
        201:
            description: Name added successfully
        400:
            description: Invalid input
        500:
            description: An error occurred
    """
    db = MongoConnectionManager.get_db()
    names_collection = db['names']
    try:
        data = request.json
        required_fields = ['FirstChar', 'content', 'category', 'id']

        # Validate required fields
        if not data or not all(field in data for field in required_fields):
            return jsonify({"error": f"Missing required fields: {required_fields}"}), 400

        name = {
            "_id": str(uuid.uuid4()),
            **data
        }
        names_collection.insert_one(name)
        return jsonify({"message": "Name added successfully", "_id": name["_id"]}), 201
    except Exception as e:
        return jsonify({"error": str(e)}), 500

# 7. Delete Name
@names_blueprint.route('/delete_name/<id>', methods=['DELETE'])
def delete_name(id):
    """
    Delete a name from the database
    ---
    parameters:
      - name: id
        in: path
        required: true
        type: string
        description: The ID of the name to delete
    responses:
        200:
            description: Name deleted successfully
        404:
            description: Name not found
        500:
            description: An error occurred
    """
    db = MongoConnectionManager.get_db()
    names_collection = db['names']
    try:
        result = names_collection.delete_one({"_id": id})
        if result.deleted_count > 0:
            return jsonify({"message": "Name deleted successfully"}), 200
        return jsonify({"error": "Name not found"}), 404
    except Exception as e:
        return jsonify({"error": str(e)}), 500
