class ExpAr:
	def operation(self):
		pass

class Operando(ExpAr):
	def __init__(self,value):
		self.value = value
	def operation(self):
		return self.value

class Operator(ExpAr):
	def __init__(self, op1, op2):
		self.op1 = op1
		self.op2 = op2

	def getop1(self):
		return self.op1
	def getop2(self):
		return self.op2

class Sum(Operator):
	def __init__(self, op1, op2):
		super().__init__(op1, op2)
	def operation(self):
		return self.getop1().operation() + self.getop2().operation()

class Mult(Operator):
	def __init__(self, op1, op2):
		super().__init__(op1, op2)
	def operation(self):
		return self.getop1().operation() * self.getop2().operation()

class Sub(Operator):
	def __init__(self, op1, op2):
		super().__init__(op1, op2)
	def operation(self):
		return self.getop1().operation() - self.getop2().operation()


class Logic:
	def Operation(self):
		pass

class Value(Logic):
	def __init__(self,value):
		self.value = value
	def operation(self):
		return self.value

class Gate2(Logic):
	def __init__(self, op1, op2):
		self.op1 = op1
		self.op2 = op2

	def getop1(self):
		return self.op1
	def getop2(self):
		return self.op2

class And(Gate2):
	def __init__(self, op1, op2):
		super().__init__(op1, op2)
	def operation(self):
		return self.getop1().operation() and self.getop2().operation()

class Or(Gate2):
	def __init__(self, op1, op2):
		super().__init__(op1, op2)
	def operation(self):
		return self.getop1().operation() or self.getop2().operation()

class Nor(Gate2):
	def __init__(self, op1, op2):
		super().__init__(op1, op2)
		OrHelper = Or(self.op1,self.op2) 
	def operation(self):
		if not (OrHelper.operation()):
			return True
		return False
		


def main():
	op1 = Operando(2)
	op2 = Operando(3)

	print(op1.operation())
	print(op2.operation())

	soma = Sum(op1,op2)
	new = Sum(soma,op2)

	m = Mult(new, op2)

	s = Sub(op1,op2)

	print(soma.operation())
	print(new.operation())
	print(m.operation())
	print(s.operation())

#main()

def teste():
	print("values")
	t = Value(True)
	f = Value(False)

	print(t.operation())
	print(f.operation())

	print("And")
	AndGate = And(t,t)
	print(AndGate.operation())
	AndGate = And(t,f)
	print(AndGate.operation())

	print("Or")
	


teste()