def infix_to_rpn(expression):
    precedence = {'+': 1, '-': 1, '*': 2, '/': 2}

    def is_operator(token):
        return token in precedence.keys()

    output = []
    operator_stack = []

    for token in expression.split():
        if token.isdigit() or token.isalpha():
            output.append(token)
        elif token == '(':
            operator_stack.append(token)
        elif token == ')':
            while operator_stack[-1] != '(':
                output.append(operator_stack.pop())
            operator_stack.pop()  # Rimuove '('
        elif is_operator(token):
            while (operator_stack and is_operator(operator_stack[-1]) and
                   precedence[token] <= precedence[operator_stack[-1]]):
                output.append(operator_stack.pop())
            operator_stack.append(token)

    while operator_stack:
        output.append(operator_stack.pop())

    return ' '.join(output)


# Esempio di utilizzo
if __name__ == "__main__":
    # infix_expression = "( 7 / 3 ) / ( ( 1 - 4 ) * 2 ) + 1"
    infix_expression = "A + B * ( C + C * E )"
    rpn_expression = infix_to_rpn(infix_expression)
    print("Espressione RPN:", rpn_expression)