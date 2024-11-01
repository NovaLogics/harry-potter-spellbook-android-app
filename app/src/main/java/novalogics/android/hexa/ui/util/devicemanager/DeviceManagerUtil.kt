package novalogics.android.hexa.ui.util.devicemanager

import android.content.Context
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DeviceManagerUtil @Inject constructor(
    @ApplicationContext private val context: Context
) : DeviceManagerUtilImpl {

    override fun deviceFlashlight(actionOnOff: Boolean) {
        val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
        val cameraId = cameraManager.cameraIdList.first()
        try {
            cameraManager.setTorchMode(cameraId, actionOnOff)
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
    }
}
