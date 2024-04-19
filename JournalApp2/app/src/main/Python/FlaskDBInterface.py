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


# Add entry
@app.route('/add', methods=['POST'])
def add():
    collection = db["journalEntries"]
    request_data = request.get_json()
    mood = request_data['mood']
    date = request_data['date']
    time = request_data['time']
    energy = request_data['energy']
    socialBattery = request_data['socialBattery']
    sleepQuality = request_data['sleepQuality']
    sleepAmount = request_data['sleepAmount']
    stomachFeeling = request_data['stomachFeeling']
    lastEaten = request_data['lastEaten']
    notes = request_data['notes']
    image = request_data['image']
    data = { "mood": mood, "date": date, "time": time, "energy": energy, "socialBattery": socialBattery, "sleepQuality": sleepQuality, "sleepAmount": sleepAmount, "stomachFeeling": stomachFeeling, "lastEaten": lastEaten, "notes": notes, "image": image}
    _id = collection.insert_one(data) 
    return json.dumps({'id' : str(_id.inserted_id)})


# Select All users

@app.route('/get_all', methods=['GET'])
def all():
    collection = db["journalEntries"] 
    entries = list(collection.find())
    # we need to convert _id to str.
    return json.dumps(entries, default=str)
    
    


if __name__ == "__main__":
    app.run("0.0.0.0",port=5000, debug=True)