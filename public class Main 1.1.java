public class Main {
    public static void main(String[] args) {
    
        def get_user_input():
       
    

            integer_value = int(input("Please enter an integer: "))
            print("Thank you for inputting the integer, I read {integer_value}.")
        except ValueError:
            print("Invalid integer input. Please try again.")
            return None, None, None
    

            float_value = float(input("Please enter a float: "))
            print("Thank you for inputting the float, I read {float_value}.")
        except ValueError:
            print("Invalid float input. Please try again.")
            return None, None, None
    
        text_value = input("Please enter a line of text: ")
        print("Thank you for inputting the line of text, I read '{text_value}'.")
    
        return integer_value, float_value, text_value
    
    if __name__ == "__main__":
        integer, float_value, text = get_user_input()
    
        if integer is not None and float_value is not None and text is not None:
            print("All input received successfully!")
}
}