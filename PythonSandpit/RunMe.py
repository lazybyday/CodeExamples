import Controllers.NumberUtils
import Controllers.ASCIIUtils
import DataModels.Entities as Entities

def main():    
    mySimpleMaths = Controllers.NumberUtils.SimpleMaths()

    #Introduction 
    headingASCIIArtConverter = Controllers.ASCIIUtils.ASCIIArtConverter()
    print("=====================================================================================")
    print(headingASCIIArtConverter.ToDoomFont("Hello Zapier") +"\n")
    print("=====================================================================================")
    print("Ready... ")
    print(headingASCIIArtConverter.ToBlockFont("321") + "   go...\n")
  
    #Do some Maths
    print("--- Do some Simple Maths ---")
    dValue = 9+20/3
    print("Starting Value: "+ str(dValue)) 
    print("Double that value is: "+ str(mySimpleMaths.Doubler(dValue)))
    print("And rounds to: "+ str(mySimpleMaths.RoundToWholeNumber(mySimpleMaths.Doubler(dValue))))

    #Play with objects 
    print("\n--- Play with Person Objects and Collections ---")
    people = [
        Entities.Person("Alec", 1981), 
        Entities.Person("Gemma", 1986), 
        Entities.Person("Hunter", 2013),
        Entities.Person("Elisa", 1978)
    ]
    if len(people) >0:
        people.pop(len(people) -1)

    for person in list(filter(lambda x: x.BirthYear < 2001, people)):
        print(person.DescriptionTxt())
        
    #looping
    print("\n--- Try some loops and things ---")
    for i in range(1,21):
        print(str(i) 
            + " -" 
            + (" even" if i % 2 == 0 else " odd") 
            + (" and Prime" if mySimpleMaths.IsPrime(i) else "")
        )

    print("All Done... bye :)")

main()