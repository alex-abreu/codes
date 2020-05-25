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

def readFile(fName):
	list = []
	file = open(fName,"r")
	for line in file:
		if "\n" in line:
			list.append(line[:-1])
		else:
			list.append(line)

	return list


class Workout:
	
	exercises = {}

	def __init__(self, push, pull, legs, abss, biceps, triceps, shoulders, cardio):
		types = ["push", "pull", "legs", "abs", "biceps","triceps","shoulders","cardio"]

		for kind in types:
			self.exercises[kind] = []
		
		for exerc in push:
			self.exercises["push"].append(exerc)
		for exerc in pull:
			self.exercises["pull"].append(exerc)
		for exerc in legs:
			self.exercises["legs"].append(exerc)
		for exerc in abss:
			self.exercises["abs"].append(exerc)
		for exerc in biceps:
			self.exercises["biceps"].append(exerc)
		for exerc in triceps:
			self.exercises["triceps"].append(exerc)
		for exerc in shoulders:
			self.exercises["shoulders"].append(exerc)	
		for exerc in cardio:
			self.exercises["cardio"].append(exerc)

	def getDic(self):
		return self.exercises

	def getReps(self, maxV):
		rep = 10 + random.randint(1,maxV)
		return str(rep)

	def getExercise(self, string):
		exercise = random.choice(self.exercises[string])
		return exercise
	
	def create_msg(self, wk):
		msg = ""
		for item in wk:
			msg = msg + item +"\n"
		return msg


	def getWorkout(self, day,max_rep):
		
		wk = []

		if day == 0 or day == 3:
			wk.append("Push workout:\n")
			for i in range(0,18):
				if i < 12:
					exerc = self.getExercise("push")
					rep = self.getReps(max_rep)
					wk.append(exerc + " for " +rep + " reps.")
				else:
					if i == 12:
						wk.append("\nBiceps\n")
					exerc = self.getExercise("biceps")
					rep = self.getReps(max_rep)
					wk.append(exerc + " for " +rep + " reps.")

		if day == 1 or day == 4:
			wk.append("Pull workout:\n")
			for i in range(0,18):
				if i < 12:
					exerc = self.getExercise("pull")
					rep = self.getReps(max_rep)
				else:
					if i == 12:
						wk.append("\nTriceps\n")
					exerc = self.getExercise("triceps")
					rep = self.getReps(max_rep)
				wk.append(exerc + " for " +rep + " reps.")

		if day == 2 or day == 5:
			wk.append("Legs workout:\n")
			for i in range(0,18):
				if i < 10:
					exerc = self.getExercise("legs")
					rep = self.getReps(max_rep)
					wk.append(exerc + " for "+ rep + " reps.")
				else:
					if i == 10:
						wk.append("\nShoulders\n")
					exerc = self.getExercise("shoulders")
					rep = self.getReps(max_rep)
					wk.append(exerc + " for "+ rep + " reps.")

		if day == 6:
			wk.append("ABS\n")
			for i in range(0,10):
				exerc = self.getExercise("abs")
				rep = self.getReps(max_rep)
				wk.append(exerc + " for "+ rep + " reps.")
			wk.append("\nCardio\n")
			for exe in self.exercises["cardio"]:
				wk.append(exe)

		return wk





if __name__ == "__main__":
	
	sender_email   = "alex.dev.abreu@gmail.com"

	receiver_email = "alex.ma.cabal@gmail.com"

	try:
		push       = readFile("push")
		pull       = readFile("pull")
		legs       = readFile("legs")
		biceps     = readFile("biceps")
		triceps    = readFile("triceps")
		shoulders  = readFile("shoulders")
		abss       = readFile("abs")
		cardio     = readFile("cardio")
		
		getO = Workout(push, pull, legs, abss, biceps, triceps, shoulders, cardio)	
		
		today = datetime.datetime.today()
		
		
		day_as_int = datetime.datetime.today().weekday()

		wk = getO.getWorkout(day_as_int,10)

		print("workout chosen")
		
		subject = f"Workout for {today:%B %d, %Y}"

		msg = getO.create_msg(wk)

		print("email formated")
		
		while True:
			
			wk_day = datetime.datetime.today().weekday()

			if wk_day != day_as_int:
				
				print("Day changed, refactoring")
				
				today = datetime.datetime.today()
				subject = f"Workout for {today:%B %d, %Y}"
				
				day_as_int = datetime.datetime.today().weekday()
				
				wk = getO.getWorkout(day_as_int,10)

				msg = getO.create_msg(wk)		
			
			temp = time.ctime()
			part = temp.split(" ")
			hour = part[3].split(":")

			print
			if hour[0] == "08":
				send_mail(msg,subject,sender_email,receiver_email)
				print("\nHave Fun!!!\n")
			time.sleep(3600)
		

	except Exception as e:
		print(e)
		
		today = datetime.datetime.today() 
		print(f"{today:%B %d, %Y}") 
