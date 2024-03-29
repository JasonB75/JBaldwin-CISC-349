import json
from matplotlib import collections
#from app import app
from pymongo import MongoClient  
from flask import request
from flask.json import jsonify
import sys
import base64
from datetime import datetime
from flask import Flask

client = MongoClient("mongodb+srv://jbaldwin:CBAE4VO2IAOtZjI9@cisc349.ni0blpr.mongodb.net/?retryWrites=true&w=majority&appName=CISC349")
db = client["cisc349"]
 
app = Flask(__name__)

# A welcome message to test our server
@app.route('/')
def index():
    return "<h1>Welcome to our server 123 !!</h1>"


# Add user
@app.route('/add', methods=['POST'])
def add():
    collection = db["customers"]
    request_data = request.get_json()
    name = request_data['name']
    address = request_data['address']
    data = { "name": name, "address": address }
    _id = collection.insert_one(data) 
    return json.dumps({'id' : str(_id.inserted_id)})

# Select All users

@app.route('/all', methods=['POST'])
def all():
    collection = db["customers"] 
    customers = list(collection.find())
    # we need to convert _id to str.
    return json.dumps(customers, default=str)

@app.route("/images", methods=["GET"])
def image_list():
    collection = db["images"]
    images = list(collection.find())
    print(f"Got {len(images)} images.")
    return json.dumps(images, default=str)

@app.route("/image", methods=["POST"])
def image_save():
    collection = db["images"]
    content = request.get_json()
    _id = collection.insert_one(content)
    return json.dumps({'id': str(_id.inserted_id)})

if __name__ == "__main__":
    app.run("0.0.0.0",port=5000)