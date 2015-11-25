package org.panda_lang.panda.core.parser.improved.essential;

import org.panda_lang.panda.core.ElementsBucket;
import org.panda_lang.panda.core.parser.improved.PandaParser;
import org.panda_lang.panda.core.parser.improved.Parser;
import org.panda_lang.panda.core.parser.improved.PatternExtractor;
import org.panda_lang.panda.core.parser.improved.SourcesDivider;
import org.panda_lang.panda.core.scheme.ObjectScheme;
import org.panda_lang.panda.core.syntax.Block;
import org.panda_lang.panda.core.syntax.IExecutable;
import org.panda_lang.panda.core.syntax.Parameter;
import org.panda_lang.panda.core.syntax.Runtime;
import org.panda_lang.panda.lang.PObject;

import java.util.Stack;

public class ConstructorParser implements Parser {

    private Block parent;

    @Override
    public Runtime parse(PandaParser pandaParser, SourcesDivider sourcesDivider, PatternExtractor extractor, Block parent, Block previous) {
        this.parent = parent;

        String source = new String(sourcesDivider.getSource());
        source = source.substring(4);

        StringBuilder node = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        String clazz = null;
        boolean s = false,
                p = false;

        char[] chars = source.toCharArray();
        for (int i = 0; i < source.length(); i++) {
            char c = chars[i];

            if (c == '"') {
                s = !s;
            } else if (s) {
                node.append(c);
                continue;
            } else if (p) {
                if (c == '(') {
                    stack.push(c);
                } else if (c == ')') {
                    stack.pop();
                    if (stack.size() == 0) {
                        break;
                    }
                }
                node.append(c);
                continue;
            } else if (node.length() == 0 && Character.isWhitespace(c)) {
                continue;
            }

            switch (c) {
                case '(':
                    clazz = node.toString();
                    node.setLength(0);
                    stack.push(c);
                    p = true;
                    break;
                case ';':
                    break;
                default:
                    node.append(c);
                    break;
            }
        }

        String params = node.toString();
        node.setLength(0);

        ParameterParser parser = new ParameterParser(pandaParser, new SourcesDivider(params), extractor, parent, previous);
        Parameter[] parameters = parser.parseLocal();

        for (final ObjectScheme os : ElementsBucket.getObjects()) {
            if (os.getName().equals(clazz)) {
                return new Runtime(null, new IExecutable() {
                    @Override
                    public PObject run(Parameter instance, Parameter... parameters) {
                        return os.getConstructorScheme().getConstructor().run(parameters);
                    }
                }, parameters);
            }
        }

        return null;
    }

    @Override
    public Block getParent() {
        return parent;
    }

}
