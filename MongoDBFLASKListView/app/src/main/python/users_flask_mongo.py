import json
from matplotlib import collections
#from app import app
from pymongo import MongoClient  
from flask import request
from flask.json import jsonify
from flask import Flask

app = Flask(__name__)

client = MongoClient("mongodb+srv://jbaldwin:CBAE4VO2IAOtZjI9@cisc349.ni0blpr.mongodb.net/?retryWrites=true&w=majority&appName=CISC349")
db = client["cisc349"]
 

# A welcome message to test our server
@app.route('/')
def index():
    return "<h1>Welcome to our server 123 !!</h1>"


# Add user
@app.route('/add', methods=['POST'])
def add():
    collection = db["users"]
    request_data = request.get_json()
    name = request_data['name']
    phone = request_data['phone']
    ssn = request_data['ssn']
    data = { "name": name, "phone": phone, "ssn" : ssn}
    _id = collection.insert_one(request_data) 
    return json.dumps({'id' : str(_id.inserted_id)})

'''@app.route('/update', methods=['POST'])
def update():
    collection = db["users"]
    request_data = request.get_json()
    _id = request_data['_id']
    name = request_data['name']
    address = request_data['address']
    phone = request_data['phone']
    SSN = request_data['SSN']
    _id = request_data['_id']
    print(f'ID{_id}, Name: {name}, Address: {address}, Phone: {phone}, SSN: {SSN}')
    filter = {'_id' : _id}
    newvalues = {"$push", {'comments': comments}}
    
    _id = collection.update_one(filter, newvalues)
    return json.dumps({'_id' : _id})'''




# Select All users

@app.route('/all', methods=['GET'])
def all():
    collection = db["users"] 
    customers = list(collection.find())
    # we need to convert _id to str.
    return json.dumps(customers, default=str)
    
    


if __name__ == "__main__":
    app.run("0.0.0.0",port=5000)