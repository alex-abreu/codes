import numpy as np
import struct

#generates numpy array from data file
def load_float_matrix(fname):
    """
    Load a numpy matrix in a binary format:
    <uint32:numRows><uint32:numCols><float32:data[]>
    where data is stored in row-major order
    """
    with open(fname, 'rb') as fd:
        num_rows = struct.unpack('=I', fd.read(4))[0]
        num_cols = struct.unpack('=I', fd.read(4))[0]
        numpy_matrix = np.zeros((num_rows, num_cols), dtype=np.float32)
        for i in range(numpy_matrix.shape[0]):
            for j in range(numpy_matrix.shape[1]):
                numpy_matrix[i, j] = struct.unpack('=f', fd.read(4))[0]
    return numpy_matrix

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

#creates a file an write the descripor in it
#adds the file name to the "PICODES.txt"
def writetxt(arraynp,fName):
	
	out = open(fName,"w")
	if(not checkDUP("PICODES.txt",fName)):
		file = open("PICODES.txt","a")
		file.write(fName+"\n")

	for item in arraynp:
		temp = (int(item))
		out.write(str(temp))
		out.write("\n")
	out.close()

#creates a list of dat files to be used
def readFile(fName):
	list = []
	file = open(fName,"r")
	for line in file:
		if "\n" in line:
			list.append(line[:-1])
		else:
			list.append(line)

	return list

def main():
	fdata = input("digite o nome do arquivo contendo a lista de .dat files\n")

	file = readFile(fdata)
		
	for f in file:
		arraynp = load_float_matrix(f)
		fname = f[:-3]+"txt"
		fname[23] = '-'
		
		writetxt(arraynp,fname)
    
    

main()