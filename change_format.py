import json

with open('groupAdminList.json', encoding='utf-8') as f:
    data = json.load(f)

for item in data:
    del item['groupFeeds']

with open('fileplus2.json', 'w', encoding='utf-8') as f:
    json.dump(data, f)
    