
class SimpleMaths:
    def __init__(self):
        pass

    def Doubler(self, numValue):
        return numValue*2    

    def RoundToWholeNumber(self, numValue):
        return round(numValue, 0)

    def IsPrime(self, numValue):
        numValue = abs(int(numValue))

        if numValue < 2:
            return False

        if numValue == 2: 
            return True    

        if not numValue & 1: 
            return False

        for x in range(3, int(numValue**0.5) + 1, 2):
            if numValue % x == 0:
                return False

        return True

