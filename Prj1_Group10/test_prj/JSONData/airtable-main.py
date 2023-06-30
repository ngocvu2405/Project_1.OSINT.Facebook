import json

base_id = 'appfpkYiYDZtMWJhA'


table_id = 'tblhmlceroOgnh6Ed'


personal_access_token = 'patDHXbaPvYn30swA.1e8a7fabfa00ccb9e2687143b1b79f46bd864fe86d10256f1ca44a4125046e45'

import requests

def create_headers():
    headers = {
    'Authorization': 'Bearer ' + str(personal_access_token),
    'Content-Type': 'application/json',
    }
    return headers

base_table_api_url = 'https://api.airtable.com/v0/{}/{}'.format(base_id, table_id)

# Open file json_data
file = str(input())
f = open(file, encoding="utf8")
data = json.load(f)

json_data = {"records": [{"fields": record} for record in data]}

# 2. Create an Entry
headers = create_headers()
response = requests.post(base_table_api_url, headers=headers, json=json_data)
print(response.content)


