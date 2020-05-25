from xml.dom import minidom

maxStorage = 100

def addDictionary(dic, key, data):
	
	if key in dic:
		dic[key].append(data)
	else:
		dic.setdefault(key,[])
		dic[key].append(data)

def checkFull(dic):
	if len(dic) >= maxStorage:
		return True
	return False

def writefile(fName, data):
	try:
		fName = fName+".txt"
		file = open(fName,"a")
		for elem in data:
			file.write(elem+".JPEG")
			file.write("\n")
	except:
		print("deu ruim")

def writeDic(data):

	keyList = data.keys()

	for key in keyList:
		writefile(key, data[key]) 

def readXML(fName, dic):
	# parse an xml file by name
	mydoc = minidom.parse(fName)

	items = mydoc.getElementsByTagName('name')
	fname = mydoc.getElementsByTagName('filename')

	setkeys = set()
	for elem in  items:
		setkeys.add(elem.firstChild.data)
	print("class tags: ",setkeys)

	# all items data
	#print('\nAll item data:')
	#for elem in items:
	#    print(elem.firstChild.data)

	#print("\nfname:")
	#for temp in fname:
	#	print(temp.firstChild.data)
	imageName = fname[0].firstChild.data
	
	for key in setkeys:
		addDictionary(dic , key , imageName)
		
	setkeys.clear()

	#writefile(key,dic[key])


#reads the file fName file and creates a list
#with the names in the file
def getList(fName):
	list = []
	file = open(fName,"r")
	for line in file:
		if "\n" in line:
			list.append(line[:-1])
		else:
			list.append(line)

	return list


def main():

	dic = {}
	listXML = input("digite o nome do arquivo com a lista dos arquivos xml\n")
	list = getList(listXML)

	count = 0
	
	last = list[-1]

	for fName in list:
		
		readXML(fName, dic)
		count = count +1
		
		if (count == maxStorage) or (fName == last):
			writeDic(dic)
			dic.clear()

	
	
	

main()