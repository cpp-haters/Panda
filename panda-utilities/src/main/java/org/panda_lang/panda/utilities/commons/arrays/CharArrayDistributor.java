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

package org.panda_lang.panda.utilities.commons.arrays;

public class CharArrayDistributor {

    private final char[] array;
    private int index;

    public CharArrayDistributor(char[] array) {
        this.array = array;
        this.index = -1;
    }

    public char previous() {
        if (index - 1 < array.length) {
            index--;

            if (index < 0) {
                index = 0;
            }

            return array[index];
        }
        else {
            return getLast();
        }
    }

    public char current() {
        return index < array.length || index > -1 ? array[index] : array[0];
    }

    public boolean hasNext() {
        return index < array.length - 1;
    }

    public char next() {
        if (index + 1 < array.length) {
            index++;
            return array[index];
        }
        else {
            return getLast();
        }
    }

    public char further() {
        if (index + 1 < array.length) {
            return array[index + 1];
        }
        else {
            return getLast();
        }
    }

    public char future() {
        if (index + 2 < array.length) {
            return array[index + 2];
        }
        else {
            return getLast();
        }
    }

    public char getPrevious(int t) {
        return index - t > 0 ? array[index - t] : array[0];
    }

    public char getPrevious() {
        return index - 1 > 0 ? array[index - 1] : array[0];
    }

    public char getLast() {
        return array[array.length - 1];
    }

}
