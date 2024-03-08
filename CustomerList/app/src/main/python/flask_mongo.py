import json
from matplotlib import collections
#from app import app
from pymongo import MongoClient  
from flask import request
from flask.json import jsonify
from flask import Flask, request

client = MongoClient("mongodb+srv://jbaldwin:CBAE4VO2IAOtZjI9@cisc349.ni0blpr.mongodb.net/?retryWrites=true&w=majority&appName=CISC349")
db = client["myFirstDatabase"]

app = Flask("customers")

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



if __name__ == "__main__":
    app.run("0.0.0.0", port=5000)