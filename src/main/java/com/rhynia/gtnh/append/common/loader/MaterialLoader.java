package com.rhynia.gtnh.append.common.loader;

import com.github.bartimaeusnek.bartworks.API.WerkstoffAdderRegistry;
import com.rhynia.gtnh.append.common.block.BlockRegister;
import com.rhynia.gtnh.append.common.item.ItemRegister;
import com.rhynia.gtnh.append.common.material.VAMaterials;

public class MaterialLoader {

    public static void load() {

        ItemRegister.registry();
        BlockRegister.registry();

        WerkstoffAdderRegistry.addWerkstoffAdder(new VAMaterials());

    }
}
