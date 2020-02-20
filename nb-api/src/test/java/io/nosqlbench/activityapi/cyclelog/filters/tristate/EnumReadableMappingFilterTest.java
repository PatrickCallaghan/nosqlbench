/*
 *
 *    Copyright 2016 jshook
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 * /
 */

package io.nosqlbench.activityapi.cyclelog.filters.tristate;

import io.nosqlbench.activityapi.cyclelog.buffers.results.ResultReadable;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Test
public class EnumReadableMappingFilterTest {

    @Test
    public void testMappingFilter() {
        TestEnumFilter f = new TestEnumFilter();
        f.addPolicy("Alpha", TristateFilter.Policy.Keep);
        f.addPolicy(".*Bet.*", TristateFilter.Policy.Discard);

        TristateFilter.Policy alphaPolicy = f.apply(TestEnum.Alpha);
        assertThat(alphaPolicy).isEqualTo(TristateFilter.Policy.Keep);

        TristateFilter.Policy betaPolicy= f.apply(TestEnum.Beta);
        assertThat(betaPolicy).isEqualTo(TristateFilter.Policy.Discard);

        TristateFilter.Policy gammaPolicy= f.apply(TestEnum.Gamma);
        assertThat(gammaPolicy).isEqualTo(TristateFilter.Policy.Ignore);

    }

    private static class TestEnumFilter extends EnumReadableMappingFilter<TestEnum> {

        public TestEnumFilter() {
            super(TestEnum.values(),Policy.Ignore);
        }
    }

    private static enum TestEnum implements ResultReadable {

        Alpha(1),
        Beta(2),
        Gamma(17);

        private final int resultCode;

        TestEnum(int resultCode) {
            this.resultCode= resultCode;
        }

        @Override
        public int getResult() {
            return resultCode;
        }
    }
}