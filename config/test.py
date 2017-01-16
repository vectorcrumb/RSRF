import yaml

with open("testbot.yaml", 'r') as stream:
	try:
		data = yaml.load(stream)
		print(yaml.dump(data, default_flow_style=False))
	except yaml.YAMLError as exc:
		print(exc)

print(data)
