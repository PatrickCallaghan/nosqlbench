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

package io.nosqlbench.activityapi.cyclelog.inputs.cyclelog;

import io.nosqlbench.activityapi.core.Activity;
import io.nosqlbench.activityapi.input.Input;
import io.nosqlbench.activityapi.input.InputDispenser;
import io.nosqlbench.activityapi.input.InputType;
import io.virtdata.annotations.Service;

@Service(InputType.class)
public class CycleLogInputType implements InputType {
    @Override
    public String getName() {
        return "cyclelog";
    }

    @Override
    public InputDispenser getInputDispenser(Activity activity) {
        return new Dispenser(activity);
    }

    public static class Dispenser implements InputDispenser {

        private final Activity activity;
        private final Input input;

        public Dispenser(Activity activity) {
            this.activity = activity;
            this.input = new CycleLogInput(activity);
        }

        @Override
        public Input getInput(long slot) {
            return input;
        }
    }

}