package dev.mruniverse.slimelib.utils;

@SuppressWarnings("unused")
public class CenterText {
    public enum Text {
        A('A'),
        a('a'),
        B('B'),
        b('b'),
        C('C'),
        c('c'),
        D('D'),
        d('d'),
        E('E'),
        e('e'),
        F('F'),
        f('f', 4),
        G('G'),
        g('g'),
        H('H'),
        h('h'),
        I('I', 3),
        i('i', 1),
        J('J'),
        j('j'),
        K('K'),
        k('k', 4),
        L('L'),
        l('l', 1),
        M('M'),
        m('m'),
        N('N'),
        n('n'),
        O('O'),
        o('o'),
        P('P'),
        p('p'),
        Q('Q'),
        q('q'),
        R('R'),
        r('r'),
        S('S'),
        s('s'),
        T('T'),
        t('t', 4),
        U('U'),
        u('u'),
        V('V'),
        v('v'),
        W('W'),
        w('w'),
        X('X'),
        x('x'),
        Y('Y'),
        y('y'),
        Z('Z'),
        z('z'),
        NUM_1('1'),
        NUM_2('2'),
        NUM_3('3'),
        NUM_4('4'),
        NUM_5('5'),
        NUM_6('6'),
        NUM_7('7'),
        NUM_8('8'),
        NUM_9('9'),
        NUM_0('0'),
        EXCLAMATION_POINT('!', 1),
        AT_SYMBOL('@', 6),
        NUM_SIGN('#'),
        DOLLAR_SIGN('$'),
        PERCENT('%'),
        UP_ARROW('^'),
        AMPERSAND('&'),
        ASTERISK('*'),
        LEFT_PARENTHESIS('(', 4),
        RIGHT_PARENTHESIS(')', 4),
        MINUS('-'),
        UNDERSCORE('_'),
        PLUS_SIGN('+'),
        EQUALS_SIGN('='),
        LEFT_CURL_BRACE('{', 4),
        RIGHT_CURL_BRACE('}', 4),
        LEFT_BRACKET('[', 3),
        RIGHT_BRACKET(']', 3),
        COLON(':', 1),
        SEMI_COLON(';', 1),
        DOUBLE_QUOTE('"', 3),
        SINGLE_QUOTE('\'', 1),
        LEFT_ARROW('<', 4),
        RIGHT_ARROW('>', 4),
        QUESTION_MARK('?'),
        SLASH('/'),
        BACK_SLASH('\\'),
        LINE('|', 1),
        TILDE('~'),
        TICK('`', 2),
        PERIOD('.', 1),
        COMMA(',', 1),
        SPACE(' ', 3),
        DEFAULT('a', 4);

        private final char character;

        private final int length;

        public char getCharacter() {
            return this.character;
        }

        public int getLength() {
            return this.length;
        }

        Text(char character) {
            this(character, 5);
        }

        Text(char character, int length) {
            this.character = character;
            this.length = length;
        }

        public int getBoldLength() {
            if (this == SPACE)
                return getLength();
            return this.length + 1;
        }

        public static Text getDefaultFontInfo(char c) {
            for (Text dFI : values()) {
                if (dFI.getCharacter() == c)
                    return dFI;
            }
            return DEFAULT;
        }
    }
    public static String sendToCenter(String replace) {
        replace = replace.replace("&", "§");
        int n = 0;
        int n2 = 0;
        boolean b = false;
        char[] charArray;
        for (int length = (charArray = replace.toCharArray()).length, i = 0; i < length; i++) {
            char c = charArray[i];
            if (c == '§') {
                n2 = 1;
            } else if (n2 != 0) {
                n2 = 0;
                b = (c == 'l' || c == 'L');
            } else {
                Text fontInfo = Text.getDefaultFontInfo(c);
                n += b ? fontInfo.getBoldLength() : fontInfo.getLength();
                n++;
            }
        }
        int n3 = 154 - n / 2;
        int n4 = 3 + 1;
        int j = 0;
        StringBuilder sb = new StringBuilder();

        while (j < n3) {
            sb.append(" ");
            j += n4;
        }

        return sb + replace;
    }
}
