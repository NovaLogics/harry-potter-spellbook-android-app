package novalogics.android.hexa.ui.util.devicemanager

import kotlinx.coroutines.flow.Flow

interface DeviceManagerUtilImpl  {

    fun deviceFlashlight( actionOnOff: Boolean )

    fun getFlashlightStatus() : Flow<Boolean>
}
