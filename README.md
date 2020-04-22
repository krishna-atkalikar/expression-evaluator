# expression-evaluator

Expression evaluator can parse a string expression and evaluate it to get result. Entry point for this is ExpressionEvaluator.


Few points to consider before evaluating expression:
1. Every token in expression must a be space separated. for eg. "( 1 + 2 ) * 5".
2. If expression contains a variable then it must start and end with "$" symbol. for eg. "( $salary$ > 0 )".