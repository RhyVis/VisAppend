package com.rhynia.gtnh.append.loader;

import com.github.bartimaeusnek.bartworks.API.WerkstoffAdderRegistry;
import com.rhynia.gtnh.append.common.item.ItemRegister;

public class LoaderMaterial {
    public static void load() {

        ItemRegister.registry();
        BlockRegister.registry();

        WerkstoffAdderRegistry.addWerkstoffAdder(new MaterialPool());

    }
}
