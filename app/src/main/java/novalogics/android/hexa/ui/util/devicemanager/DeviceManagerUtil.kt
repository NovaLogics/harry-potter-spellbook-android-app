package novalogics.android.hexa.ui.util.devicemanager

import android.content.Context
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class DeviceManagerUtil @Inject constructor(
    @ApplicationContext private val context: Context
) : DeviceManagerUtilImpl {

    private val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager

    override fun deviceFlashlight(actionOnOff: Boolean) {

        val cameraId = cameraManager.cameraIdList.first()
        try {
            cameraManager.setTorchMode(cameraId, actionOnOff)
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
    }

    override fun getFlashlightStatus(): Flow<Boolean> = callbackFlow {
        cameraManager.cameraIdList.firstOrNull() ?: run {
            close()  // If no camera is found, close the flow
            return@callbackFlow
        }

        // TorchCallback to listen for flashlight state changes
        val torchCallback = object : CameraManager.TorchCallback() {
            override fun onTorchModeChanged(cameraId: String, enabled: Boolean) {
                trySend(enabled)
            }
        }

        // Register the callback
        cameraManager.registerTorchCallback(torchCallback, null)

        // Clean up callback when the flow is closed
        awaitClose {
            cameraManager.unregisterTorchCallback(torchCallback)
        }
    }

}
