package com.rhynia.gtnh.append.common.loader;

import static com.rhynia.gtnh.append.api.enums.VA_Values.VM;

import com.github.bartimaeusnek.bartworks.API.WerkstoffAdderRegistry;
import com.rhynia.gtnh.append.common.block.BlockRegister;
import com.rhynia.gtnh.append.common.item.ItemRegister;

public class MaterialLoader {

    public static void load() {

        ItemRegister.registry();
        BlockRegister.registry();

        WerkstoffAdderRegistry.addWerkstoffAdder(VM);

    }
}
