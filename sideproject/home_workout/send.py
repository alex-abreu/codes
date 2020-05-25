import random
import datetime
import smtplib
import time

def send_mail(message, subject, sender_email, receiver_email):
	password = "uuqncqmuhbfscpvg"
	
	server = smtplib.SMTP('smtp.gmail.com',587)
	server.ehlo()
	server.starttls()
	server.ehlo()
	server.login(sender_email, password)
	
	msg =f"Subject: {subject} \n\n{message}"
	
	server.sendmail(sender_email, receiver_email, msg)

	print(f"Email With {subject} Sent!")


msg = "teste msg"

sender_email   = "alex.dev.abreu@gmail.com"

receiver_email = "alex.ma.cabal@gmail.com"

today = datetime.datetime.today()
subject = f"Workout for {today:%B %d, %Y}"

temp = time.ctime()
part = temp.split(" ")
hour = part[3].split(":")

if hour[0] == "07":
	print("yes")