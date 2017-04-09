/*
 * Copyright (c) 2015-2017 Dzikoysk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.panda_lang.panda.language.structure.block.looping;

import org.panda_lang.panda.core.interpreter.lexer.pattern.TokenHollowRedactor;
import org.panda_lang.panda.core.interpreter.lexer.pattern.TokenPattern;
import org.panda_lang.panda.core.interpreter.lexer.pattern.TokenPatternHollows;
import org.panda_lang.panda.core.interpreter.lexer.pattern.TokenPatternUtils;
import org.panda_lang.panda.core.interpreter.parser.pipeline.DefaultPipelines;
import org.panda_lang.panda.core.interpreter.parser.pipeline.registry.ParserRegistration;
import org.panda_lang.panda.framework.implementation.parser.PandaParserException;
import org.panda_lang.panda.framework.language.interpreter.parser.ParserInfo;
import org.panda_lang.panda.framework.language.interpreter.parser.UnifiedParser;
import org.panda_lang.panda.framework.language.interpreter.token.TokenizedSource;
import org.panda_lang.panda.language.PandaSyntax;
import org.panda_lang.panda.language.structure.block.looping.blocks.WhileBlock;
import org.panda_lang.panda.language.structure.expression.Expression;
import org.panda_lang.panda.language.structure.expression.ExpressionParser;

@ParserRegistration(target = DefaultPipelines.BLOCK, parserClass = WhileParser.class, handlerClass = WhileParserHandler.class)
public class WhileParser implements UnifiedParser {

    protected static final TokenPattern PATTERN = TokenPattern.builder().compile(PandaSyntax.getInstance(), "while ( +* )").build();

    @Override
    public void parse(ParserInfo info) {
        TokenPatternHollows hollows = TokenPatternUtils.extract(PATTERN, info);
        TokenHollowRedactor redactor = new TokenHollowRedactor(hollows);

        redactor.map("while-expression");
        TokenizedSource expressionSource = redactor.get("while-expression");

        ExpressionParser expressionParser = new ExpressionParser();
        Expression expression = expressionParser.parse(info, expressionSource);

        if (!expression.getReturnType().getClassName().equals("Boolean")) {
            throw new PandaParserException("Loop requires boolean as an argument");
        }

        info.setComponent("block", new WhileBlock(expression));
    }

}
