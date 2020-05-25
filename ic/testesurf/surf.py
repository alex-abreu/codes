import cv2
import numpy as np

def SURF(iName): 
	img = cv2.imread(iName)
	gray= cv2.cvtColor(img,cv2.COLOR_BGR2GRAY)

	surf = cv2.xfeatures2d.SURF_create(extended=True)
	
	#kp = surf.detect(gray,None)
	
	#generates an image with the keypoints drawn
	#uncomment to use
	#img=cv2.drawKeypoints(img,kp, None)
	#cv2.imwrite('sift_keypoints.jpg',img)

	kp1, des1 = surf.detectAndCompute(img,None)
	print(des1.shape)
	return des1
#checks for duplicated names on the list
#in case the program is run more than once avoids
#adding the same txt to the list
def checkDUP(fName,name):
	list = []
	try:
		file = open(fName,"r")
		for line in file:
			if "\n" in line:
				list.append(line[:-1])
			else:
				list.append(line)
		if name in list:
			return True
		else:
			return False
	except:
		return False

#reads the file fName file and creates a list
#with the names in the file
def readFile(fName):
	list = []
	file = open(fName,"r")
	for line in file:
		if "\n" in line:
			list.append(line[:-1])
		else:
			list.append(line)

	return list

#writes the txt file with the features
def writeNumpy(fName,features):
	c = np.savetxt(fName, features, delimiter = ' ')

#adds the file name to the SIFT.txt list and
#creates/fill the file with the features
def writeSURF(fName, features):
	name = fName[:-4]+"_SURF.txt"
	if(not checkDUP("SURF",name)):
		file = open("SURF","a")
		file.write(name+"\n")
		file.close()
		list = toList(features)
		print(features.size)
		print(len(list))
		writeNumpy(name,features)
		#writeFile(name,list)

def toList(npArray):
	list = []
	for row in npArray:
		for cell in row:
			list.append(cell)
	return list

def writeFile(name,lista):
	file = open(name,"w")
	for item in lista:
		file.write(str(item))
		file.write("\n")
	file.close()

def main():
	
	imageListFile = input("digite o nome do arquivo contendo a lista das imagens:")
	
	imageList = readFile(imageListFile)
	print(imageList)
	for image in imageList:
		
		features = SURF(image)
		writeSURF(image,features)

		#writeNumpy(image,features)

main()