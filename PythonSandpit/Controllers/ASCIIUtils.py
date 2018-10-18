
class ASCIIArtConverter:
    def __init__(self):
        pass

    def _ConvertTextToFont (self, strText, dictFont):
        strReturn = ""
        aryCharLines = []

        for character in strText.upper():            
            if character in dictFont:
                charToConvert = character
            else:
                charToConvert = " "          

            aryChar = str(dictFont[charToConvert]).splitlines()                   
            
            for i in range (0, len(aryChar)):            
                if (len(aryCharLines) <= i):
                    aryCharLines.append(aryChar[i])
                else:                
                    aryCharLines[i] +=aryChar[i]
            
        for characters in aryCharLines:
            strReturn += characters +"\n"

        return str.rstrip(strReturn)              

    def ToBlockFont(self, strText):
        dictFont = {
            "0": " .----------------. \n| .--------------. |\n| |     ____     | |\n| |   .'    '.   | |\n| |  |  .--.  |  | |\n| |  | |    | |  | |\n| |  |  `--'  |  | |\n| |   '.____.'   | |\n| |              | |\n| '--------------' |\n '----------------' ",
            "1": " .----------------. \n| .--------------. |\n| |     __       | |\n| |    /  |      | |\n| |    `| |      | |\n| |     | |      | |\n| |    _| |_     | |\n| |   |_____|    | |\n| |              | |\n| '--------------' |\n '----------------' ",
            "2": " .----------------. \n| .--------------. |\n| |    _____     | |\n| |   / ___ `.   | |\n| |  |_/___) |   | |\n| |   .'____.'   | |\n| |  / /____     | |\n| |  |_______|   | |\n| |              | |\n| '--------------' |\n '----------------' ",
            "3": " .----------------. \n| .--------------. |\n| |    ______    | |\n| |   / ____ `.  | |\n| |   `'  __) |  | |\n| |   _  |__ '.  | |\n| |  | \\____) |  | |\n| |   \\______.'  | |\n| |              | |\n| '--------------' |\n '----------------' ",
            "4": " .----------------. \n| .--------------. |\n| |   _    _     | |\n| |  | |  | |    | |\n| |  | |__| |_   | |\n| |  |____   _|  | |\n| |      _| |_   | |\n| |     |_____|  | |\n| |              | |\n| '--------------' |\n '----------------' ",
            "5": " .----------------. \n| .--------------. |\n| |   _______    | |\n| |  |  _____|   | |\n| |  | |____     | |\n| |  '_.____''.  | |\n| |  | \\____) |  | |\n| |   \\______.'  | |\n| |              | |\n| '--------------' |\n '----------------' ",
            "6": " .----------------. \n| .--------------. |\n| |    ______    | |\n| |  .' ____ \\   | |\n| |  | |____\\_|  | |\n| |  | '____`'.  | |\n| |  | (____) |  | |\n| |  '.______.'  | |\n| |              | |\n| '--------------' |\n '----------------' ",
            "7": " .----------------. \n| .--------------. |\n| |   _______    | |\n| |  |  ___  |   | |\n| |  |_/  / /    | |\n| |      / /     | |\n| |     / /      | |\n| |    /_/       | |\n| |              | |\n| '--------------' |\n '----------------' ",
            "8": " .----------------. \n| .--------------. |\n| |     ____     | |\n| |   .' __ '.   | |\n| |   | (__) |   | |\n| |   .`____'.   | |\n| |  | (____) |  | |\n| |  `.______.'  | |\n| |              | |\n| '--------------' |\n '----------------' ",
            "9": " .----------------. \n| .--------------. |\n| |    ______    | |\n| |  .' ____ '.  | |\n| |  | (____) |  | |\n| |  '_.____. |  | |\n| |  | \\____| |  | |\n| |   \\______,'  | |\n| |              | |\n| '--------------' |\n '----------------' ",
            "A": " .----------------. \n| .--------------. |\n| |      __      | |\n| |     /  \\     | |\n| |    / /\\ \\    | |\n| |   / ____ \\   | |\n| | _/ /    \\ \\_ | |\n| ||____|  |____|| |\n| |              | |\n| '--------------' |\n '----------------' ",
            "B": " .----------------. \n| .--------------. |\n| |   ______     | |\n| |  |_   _ \\    | |\n| |    | |_) |   | |\n| |    |  __'.   | |\n| |   _| |__) |  | |\n| |  |_______/   | |\n| |              | |\n| '--------------' |\n '----------------' ",
            "C": " .----------------. \n| .--------------. |\n| |     ______   | |\n| |   .' ___  |  | |\n| |  / .'   \\_|  | |\n| |  | |         | |\n| |  \\ `.___.'\\  | |\n| |   `._____.'  | |\n| |              | |\n| '--------------' |\n '----------------' ",
            "D": " .----------------. \n| .--------------. |\n| |  ________    | |\n| | |_   ___ `.  | |\n| |   | |   `. \\ | |\n| |   | |    | | | |\n| |  _| |___.' / | |\n| | |________.'  | |\n| |              | |\n| '--------------' |\n '----------------' ",
            "E": " .----------------. \n| .--------------. |\n| |  _________   | |\n| | |_   ___  |  | |\n| |   | |_  \\_|  | |\n| |   |  _|  _   | |\n| |  _| |___/ |  | |\n| | |_________|  | |\n| |              | |\n| '--------------' |\n '----------------' ",
            "F": " .----------------. \n| .--------------. |\n| |  _________   | |\n| | |_   ___  |  | |\n| |   | |_  \\_|  | |\n| |   |  _|      | |\n| |  _| |_       | |\n| | |_____|      | |\n| |              | |\n| '--------------' |\n '----------------' ",
            "G": " .----------------. \n| .--------------. |\n| |    ______    | |\n| |  .' ___  |   | |\n| | / .'   \\_|   | |\n| | | |    ____  | |\n| | \\ `.___]  _| | |\n| |  `._____.'   | |\n| |              | |\n| '--------------' |\n '----------------' ",
            "H": " .----------------. \n| .--------------. |\n| |  ____  ____  | |\n| | |_   ||   _| | |\n| |   | |__| |   | |\n| |   |  __  |   | |\n| |  _| |  | |_  | |\n| | |____||____| | |\n| |              | |\n| '--------------' |\n '----------------' ",
            "I": " .----------------. \n| .--------------. |\n| |     _____    | |\n| |    |_   _|   | |\n| |      | |     | |\n| |      | |     | |\n| |     _| |_    | |\n| |    |_____|   | |\n| |              | |\n| '--------------' |\n '----------------' ",
            "J": " .----------------. \n| .--------------. |\n| |     _____    | |\n| |    |_   _|   | |\n| |      | |     | |\n| |   _  | |     | |\n| |  | |_' |     | |\n| |  `.___.'     | |\n| |              | |\n| '--------------' |\n '----------------' ",
            "K": " .----------------. \n| .--------------. |\n| |  ___  ____   | |\n| | |_  ||_  _|  | |\n| |   | |_/ /    | |\n| |   |  __'.    | |\n| |  _| |  \\ \\_  | |\n| | |____||____| | |\n| |              | |\n| '--------------' |\n '----------------' ",
            "L": " .----------------. \n| .--------------. |\n| |   _____      | |\n| |  |_   _|     | |\n| |    | |       | |\n| |    | |   _   | |\n| |   _| |__/ |  | |\n| |  |________|  | |\n| |              | |\n| '--------------' |\n '----------------' ",
            "M": " .----------------. \n| .--------------. |\n| | ____    ____ | |\n| ||_   \\  /   _|| |\n| |  |   \\/   |  | |\n| |  | |\\  /| |  | |\n| | _| |_\\/_| |_ | |\n| ||_____||_____|| |\n| |              | |\n| '--------------' |\n '----------------' ",
            "N": " .-----------------.\n| .--------------. |\n| | ____  _____  | |\n| ||_   \\|_   _| | |\n| |  |   \\ | |   | |\n| |  | |\\ \\| |   | |\n| | _| |_\\   |_  | |\n| ||_____|\\____| | |\n| |              | |\n| '--------------' |\n '----------------' ",
            "O": " .----------------. \n| .--------------. |\n| |     ____     | |\n| |   .'    `.   | |\n| |  /  .--.  \\  | |\n| |  | |    | |  | |\n| |  \\  `--'  /  | |\n| |   `.____.'   | |\n| |              | |\n| '--------------' |\n '----------------' ",
            "P": " .----------------. \n| .--------------. |\n| |   ______     | |\n| |  |_   __ \\   | |\n| |    | |__) |  | |\n| |    |  ___/   | |\n| |   _| |_      | |\n| |  |_____|     | |\n| |              | |\n| '--------------' |\n '----------------' ",
            "Q": " .----------------. \n| .--------------. |\n| |    ___       | |\n| |  .'   '.     | |\n| | /  .-.  \\    | |\n| | | |   | |    | |\n| | \\  `-'  \\_   | |\n| |  `.___.\\__|  | |\n| |              | |\n| '--------------' |\n '----------------' ",
            "R": " .----------------. \n| .--------------. |\n| |  _______     | |\n| | |_   __ \\    | |\n| |   | |__) |   | |\n| |   |  __ /    | |\n| |  _| |  \\ \\_  | |\n| | |____| |___| | |\n| |              | |\n| '--------------' |\n '----------------' ",
            "S": " .----------------. \n| .--------------. |\n| |    _______   | |\n| |   /  ___  |  | |\n| |  |  (__ \\_|  | |\n| |   \".___`-.   | |\n| |  |`\\____) |  | |\n| |  |_______.'  | |\n| |              | |\n| '--------------' |\n '----------------' ",
            "T": " .----------------. \n| .--------------. |\n| |  _________   | |\n| | |  _   _  |  | |\n| | |_/ | | \\_|  | |\n| |     | |      | |\n| |    _| |_     | |\n| |   |_____|    | |\n| |              | |\n| '--------------' |\n '----------------' ",
            "U": " .----------------. \n| .--------------. |\n| | _____  _____ | |\n| ||_   _||_   _|| |\n| |  | |    | |  | |\n| |  | '    ' |  | |\n| |   \\ `--' /   | |\n| |    `.__.'    | |\n| |              | |\n| '--------------' |\n '----------------' ",
            "V": " .----------------. \n| .--------------. |\n| | ____   ____  | |\n| ||_  _| |_  _| | |\n| |  \\ \\   / /   | |\n| |   \\ \\ / /    | |\n| |    \\ ' /     | |\n| |     \\_/      | |\n| |              | |\n| '--------------' |\n '----------------' ",
            "W": " .----------------. \n| .--------------. |\n| | _____  _____ | |\n| ||_   _||_   _|| |\n| |  | | /\\ | |  | |\n| |  | |/  \\| |  | |\n| |  |   /\\   |  | |\n| |  |__/  \\__|  | |\n| |              | |\n| '--------------' |\n '----------------' ",
            "X": " .----------------. \n| .--------------. |\n| |  ____  ____  | |\n| | |_  _||_  _| | |\n| |   \\ \\  / /   | |\n| |    > `' <    | |\n| |  _/ /'`\\ \\_  | |\n| | |____||____| | |\n| |              | |\n| '--------------' |\n '----------------' ",
            "Y": " .----------------. \n| .--------------. |\n| |  ____  ____  | |\n| | |_  _||_  _| | |\n| |   \\ \\  / /   | |\n| |    \\ \\/ /    | |\n| |    _|  |_    | |\n| |   |______|   | |\n| |              | |\n| '--------------' |\n '----------------' ",
            "Z": " .----------------. \n| .--------------. |\n| |   ________   | |\n| |  |  __   _|  | |\n| |  |_/  / /    | |\n| |     .'.' _   | |\n| |   _/ /__/ |  | |\n| |  |________|  | |\n| |              | |\n| '--------------' |\n '----------------' ",
            " ": "                    \n                    \n                    \n                    \n                    \n                    \n                    \n                    \n                    \n                    \n                    "
        }

        return self._ConvertTextToFont (strText, dictFont)


    def ToDoomFont(self, strText):
        dictFont =  {
            "0": " _____ \n|  _  |\n| |/' |\n|  /| |\n\\ |_/ /\n \\___/ \n       \n       ",
            "1": " __  \n/  | \n`| | \n | | \n_| |_\n\\___/\n     \n     ",
            "2": " _____ \n/ __  \\\n`' / /'\n  / /  \n./ /___\n\\_____/\n       \n       ",
            "3": " _____ \n|____ |\n    / /\n    \\ \\\n.___/ /\n\\____/ \n       \n       ",
            "4": "   ___ \n  /   |\n / /| |\n/ /_| |\n\\___  |\n    |_/\n       \n       ",
            "5": " _____ \n|  ___|\n|___ \\ \n    \\ \\\n/\\__/ /\n\\____/ \n       \n       ",
            "6": "  ____ \n / ___|\n/ /___ \n| ___ \\\n| \\_/ |\n\\_____/\n       \n       ",
            "7": " ______\n|___  /\n   / / \n  / /  \n./ /   \n\\_/    \n       \n       ",
            "8": " _____ \n|  _  |\n \\ V / \n / _ \\ \n| |_| |\n\\_____/\n       \n       ",
            "9": " _____ \n|  _  |\n| |_| |\n\\____ |\n.___/ /\n\\____/ \n       \n       ",
            "A": "  ___  \n / _ \\ \n/ /_\\ \\\n|  _  |\n| | | |\n\\_| |_/\n       \n       ",
            "B": "______ \n| ___ \\\n| |_/ /\n| ___ \\\n| |_/ /\n\\____/ \n       \n       ",
            "C": " _____ \n/  __ \\\n| /  \\/\n| |    \n| \\__/\\\n \\____/\n       \n       ",
            "D": "______ \n|  _  \\\n| | | |\n| | | |\n| |/ / \n|___/  \n       \n       ",
            "E": " _____ \n|  ___|\n| |__  \n|  __| \n| |___ \n\\____/ \n       \n       ",
            "F": "______ \n|  ___|\n| |_   \n|  _|  \n| |    \n\\_|    \n       \n       ",
            "G": " _____ \n|  __ \\\n| |  \\/\n| | __ \n| |_\\ \\\n \\____/\n       \n       ",
            "H": " _   _ \n| | | |\n| |_| |\n|  _  |\n| | | |\n\\_| |_/\n       \n       ",
            "I": " _____ \n|_   _|\n  | |  \n  | |  \n _| |_ \n \\___/ \n       \n       ",
            "J": "   ___ \n  |_  |\n    | |\n    | |\n/\\__/ /\n\\____/ \n       \n       ",
            "K": " _   __\n| | / /\n| |/ / \n|    \\ \n| |\\  \\\n\\_| \\_/\n       \n       ",
            "L": " _     \n| |    \n| |    \n| |    \n| |____\n\\_____/\n       \n       ",
            "M": "___  ___\n|  \\/  |\n| .  . |\n| |\\/| |\n| |  | |\n\\_|  |_/\n        \n        ",
            "N": " _   _ \n| \\ | |\n|  \\| |\n| . ` |\n| |\\  |\n\\_| \\_/\n       \n       ",
            "O": " _____ \n|  _  |\n| | | |\n| | | |\n\\ \\_/ /\n \\___/ \n       \n       ",
            "P": "______ \n| ___ \\\n| |_/ /\n|  __/ \n| |    \n\\_|    \n       \n       ",
            "Q": " _____ \n|  _  |\n| | | |\n| | | |\n\\ \\/' /\n \\_/\\_\\\n       \n       ",
            "R": "______ \n| ___ \\\n| |_/ /\n|    / \n| |\\ \\ \n\\_| \\_|\n       \n       ",
            "S": " _____ \n/  ___|\n\\ `--. \n `--. \\\n/\\__/ /\n\\____/ \n       \n       ",
            "T": " _____ \n|_   _|\n  | |  \n  | |  \n  | |  \n  \\_/  \n       \n       ",
            "U": " _   _ \n| | | |\n| | | |\n| | | |\n| |_| |\n \\___/ \n       \n       ",
            "V": " _   _ \n| | | |\n| | | |\n| | | |\n\\ \\_/ /\n \\___/ \n       \n       ",
            "W": " _    _ \n| |  | |\n| |  | |\n| |/\\| |\n\\  /\\  /\n \\/  \\/ \n        \n        ",
            "X": "__   __\n\\ \\ / /\n \\ V / \n /   \\ \n/ /^\\ \\\n\\/   \\/\n       \n       ",
            "Y": "__   __\n\\ \\ / /\n \\ V / \n  \\ /  \n  | |  \n  \\_/  \n       \n       ",
            "Z": " ______\n|___  /\n   / / \n  / /  \n./ /___\n\\_____/\n       \n       ",
            " ": "       \n       \n       \n       \n       \n       \n       \n       "
        }

        return self._ConvertTextToFont (strText, dictFont)     
