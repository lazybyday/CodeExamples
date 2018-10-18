from datetime import datetime

class Person:
    Name = None
    BirthYear = 0

    def __init__(self, name, birthYear):
        self.Name = name
        self.BirthYear = birthYear

    def DescriptionTxt(self):
        return ("* Hello my name is " + self.Name +"\n"+ 
        "  I was born in "+ str(self.BirthYear) + " therefore i am about "+ str(datetime.now().year - self.BirthYear) +" years old\n")
