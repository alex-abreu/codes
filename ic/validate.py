#reads the file fName file and creates a list
#with the names in the file
def getList(fName):
	list = []
	try:
		file = open(fName,"r")
		for line in file:
			if "\n" in line:
				list.append(line[:-1])
			else:
				list.append(line)
	except:
		print("file not found:",fName)

	return list

def validate(imageName, fList):

	for name in fList:
		try:
			file = open(name,"r")

			for line in file:
				if "\n" in line:
					temp = line[:-1]
					if temp == imageName:
						file.close()
						return True
				else:
					temp = line
					if temp == imageName:
						file.close()
						return True
			file.close()
		except:
			print("file not found: ",name)

	return False

def main():
	
	fName = input("digite o nome do arquivo com a lista de imagens\n")
	fClass = input("digite o nome do arquivo que contem a lista de classes\n")
	
	list = []
	try:
		file = open(fName,"r")
	
		classList = getList(fClass)

		for line in file:
			if "\n" in line:
				temp = line[:-1]
			else:
				temp = line
			if not validate(temp, classList):
				list.append(temp)
			else:
				print("found : ", temp)

		for item in list:
			print("xml not found :",item)
	except:
		print("erro")


main()