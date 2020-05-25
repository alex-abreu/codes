import time

while True:
	temp = time.ctime()
	part = temp.split(" ")

	hour = part[4].split(":")

	if hour[0] == "07":
		print("works")
		print(hour)
		print(hour[0])
		#do something
	time.sleep(60)