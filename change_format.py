import json

with open('groupFeed.json', encoding='utf-8') as f:
    data = json.load(f)

for item in data:
    del item['message']
    del item['createdDate']
    del item['id']

with open('fileplus.json', 'w', encoding='utf-8') as f:
    json.dump(data, f)