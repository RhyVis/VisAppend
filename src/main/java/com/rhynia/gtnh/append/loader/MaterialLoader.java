package com.rhynia.gtnh.append.loader;

import com.github.bartimaeusnek.bartworks.API.WerkstoffAdderRegistry;
import com.rhynia.gtnh.append.common.block.BlockRegister;
import com.rhynia.gtnh.append.common.item.ItemRegister;
import com.rhynia.gtnh.append.common.material.VA_GregtechMaterialPool;
import com.rhynia.gtnh.append.common.material.VA_WerkstoffMaterialPool;

public class MaterialLoader {

    public static void load() {

        ItemRegister.registry();
        BlockRegister.registry();

        new VA_GregtechMaterialPool();
        WerkstoffAdderRegistry.addWerkstoffAdder(new VA_WerkstoffMaterialPool());

    }
}
