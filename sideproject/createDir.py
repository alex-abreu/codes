# importing libraries 
import subprocess 
import os 
import glob
import shutil

def readFile(fName):
	list = []
	file = open(fName,"r")
	for line in file:
		if "\n" in line:
			list.append(line[:-1])
		else:
			list.append(line)

	return list

def listAll():
	path = "/home/alex/Documents/SIFT_Caltec/"##origem
	for i in range(100):
		fullpath = os.path.join(path,str(i)):
		createList(fullpath)

def createList(direc):
	fName = "fList"
	fullpathF = direc+"/"+fName
	f= open(fullpathF, "w")
	for file in os.listdir(dirName):
		if file != "fList":
			f.write(file)
			f.write('\n')
	f.close()

def createDir(dirName):
	subprocess.run(["mkdir", dirName])

def moveF(fName, dirName):##image name , diretorio
	path = "/home/alex/Documents/SIFT_Caltec/"##origem
	fullpath = os.path.join(path, fName)
	dest_folder ="/home/alex/Documents/SIFT_Caltec/"+dirName##destino
	shutil.move(fullpath, dest_folder)

	
def createAll():
	for i in range(100):
		createDir(str(i))

def moveAll():
	ImgList = "SIFT.txt"

	listImg = readFile(ImgList)

	j = 0
	count = 0

	for item in listImg:
		moveF(item,str(j))
		count = count + 1
		if(count % 25 ==0):
			j = j +1 

if __name__ == '__main__':

	createAll()
	moveAll()
	listAll()
	