package com.zoomtest

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import us.zoom.sdk.JoinMeetingOptions
import us.zoom.sdk.JoinMeetingParam4WithoutLogin
import us.zoom.sdk.MeetingError
import us.zoom.sdk.MeetingServiceListener
import us.zoom.sdk.MeetingStatus
import us.zoom.sdk.MeetingStatus.MEETING_STATUS_FAILED
import us.zoom.sdk.MeetingStatus.MEETING_STATUS_IDLE
import us.zoom.sdk.MeetingViewsOptions
import us.zoom.sdk.ZoomError
import us.zoom.sdk.ZoomSDK
import us.zoom.sdk.ZoomSDKInitParams
import us.zoom.sdk.ZoomSDKInitializeListener

class MainActivity : AppCompatActivity(), ZoomSDKInitializeListener, MeetingServiceListener {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

      val mainHandler = Handler(Looper.getMainLooper())
      val myRunnable =
        Runnable {
          setZoomData(
            "",
            "HhAet_HFQfeCMyD6_MBc_A",
            "87314989441",
            "eyJ0eXAiOiJKV1QiLCJzdiI6IjAwMDAwMSIsInptX3NrbSI6InptX28ybSIsImFsZyI6IkhTMjU2In0.eyJhdWQiOiJjbGllbnRzbSIsInVpZCI6IkhoQWV0X0hGUWZlQ015RDZfTUJjX0EiLCJpc3MiOiJ3ZWIiLCJzayI6IjAiLCJzdHkiOjk5LCJ3Y2QiOiJ1czA2IiwiY2x0IjowLCJleHAiOjE2NDc3MjM2MDAsImlhdCI6MTY0NzY5MzY0NywiYWlkIjoidmVreEFVMzBSNzZldmFqSTE4Z1MtUSIsImNpZCI6IiJ9.38VXitD-bFFnaP8nd8r-aMP2wUhcQ6BX6kVoe4qOFok\",\"init_sdk_jwt_token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhcHBLZXkiOiJGOG9uYnloNnlBU1BYNGRUdGhZVG5UT3hzWUJORkRXSXBORkEiLCJpYXQiOjE2NDc2OTM2NDcsImV4cCI6MTY0Nzc4MDA0NywidG9rZW5FeHAiOjE2NDc3ODAwNDd9.GCqY3ZdLGZO9GcUuu9WzTTnvAzRv3ooEQ-YzJnHEMSM",
            "password",
            "yassien y"
          )
        }
      mainHandler.post(myRunnable)
  }

  var sessionID: String? = null
  var userId: String? = null
  var meetingId: String? = null
  var token: String? = null
  var password: String? = null
  var name: String? = null

  private var mZoomSDK: ZoomSDK? = null

  fun setZoomData(sessionID: String?, userId: String?, meetingId: String?, token: String?, password: String?, name: String?) {
    this.sessionID = sessionID
    this.userId = userId
    this.meetingId = meetingId
    this.token = token
    this.password = password
    this.name = name
    initZoomData()
  }

  private fun initZoomData() {
    if (mZoomSDK == null) {
      mZoomSDK = ZoomSDK.getInstance()
      val initParams = ZoomSDKInitParams()
      initParams.appKey = "F8onbyh6yASPX4dTthYTnTOxsYBNFDWIpNFA"
      initParams.appSecret = "o2CzPwtW8nhgYUOvifRqxv27oQUB54g9nQD3"
      initParams.enableLog = true
      initParams.enableGenerateDump = true
      initParams.logSize = 5
      mZoomSDK!!.initialize(this, this, initParams)
    } else {
      setMeetingLang()
      if (!mZoomSDK!!.isInitialized) {
        Log.e("//////", "ZoomSDK has not been initialized successfully")
        return
      }
      val meetingService = mZoomSDK!!.meetingService ?: return
      if (meetingService.meetingStatus == MEETING_STATUS_IDLE) {
        startMeeting()
      } else {
        meetingService.returnToMeeting(this)
      }
    }
  }

  private fun setMeetingLang() {
//    if (ConfigurationFile.getCurrentLanguage(this).equals("ar")) {
//      mZoomSDK!!.setSdkLocale(this, Locale.forLanguageTag("ar"))
//    } else {
//      mZoomSDK!!.setSdkLocale(this, Locale.forLanguageTag("en"))
//    }
  }

  override fun onZoomSDKInitializeResult(errorCode: Int, internalErrorCode: Int) {
    Log.i("/////", "onZoomSDKInitializeResult, errorCode=$errorCode, internalErrorCode=$internalErrorCode")
    if (errorCode != ZoomError.ZOOM_ERROR_SUCCESS) {
      Log.e("//////", "Failed to initialize Zoom SDK. Error: $errorCode, internalErrorCode=$internalErrorCode")
    } else {
      registerMeetingServiceListener()
      startMeeting()
    }
  }

  private fun registerMeetingServiceListener() {
    setMeetingLang()
    val meetingService = mZoomSDK!!.meetingService
    meetingService?.addListener(this)
  }

  override fun onDestroy() {
    val zoomSDK = ZoomSDK.getInstance()
    if (zoomSDK.isInitialized) {
      val meetingService = zoomSDK.meetingService
      meetingService.removeListener(this)
    }
    super.onDestroy()
  }

  fun startMeeting() {
    if (!mZoomSDK!!.isInitialized) {
      Log.e("/////", "ZoomSDK has not been initialized successfully")
      return
    }
    if (meetingId == null) {
      Log.e("//////", "MEETING_ID in Constants can not be NULL")
      return
    }
    val meetingService = mZoomSDK!!.meetingService
    val opts = JoinMeetingOptions()
    opts.no_driving_mode = true
    opts.no_invite = true
    opts.no_meeting_end_message = false
    opts.no_meeting_error_message = true
    opts.no_titlebar = false
    opts.no_bottom_toolbar = false
    opts.no_dial_in_via_phone = true
    opts.no_dial_out_to_phone = true
    opts.no_disconnect_audio = true
    opts.no_share = true
    opts.no_video = true
    opts.meeting_views_options = 45
    opts.meeting_views_options = MeetingViewsOptions.NO_BUTTON_PARTICIPANTS
    opts.invite_options = 255
    opts.participant_id = null;
    opts.custom_meeting_id = null
    opts.no_unmute_confirm_dialog = false
    opts.no_webinar_register_dialog = true
    //opts.no_chat_msg_toast = true
    val params = JoinMeetingParam4WithoutLogin()
    params.userId = userId
    params.displayName = name
    params.meetingNo = meetingId
    params.password = password
    params.zoomAccessToken = token
    val ret = meetingService.joinMeetingWithParams(this, params, opts)
  }

  override fun onMeetingStatusChanged(meetingStatus: MeetingStatus, errorCode: Int, internalErrorCode: Int) {
    if (meetingStatus == MEETING_STATUS_FAILED && errorCode == MeetingError.MEETING_ERROR_CLIENT_INCOMPATIBLE) {
      Toast.makeText(this, "Version of ZoomSDK is too low!", Toast.LENGTH_LONG).show()
    }
    Log.e("//////", "//////// changed " + meetingStatus.name + "//" + errorCode + "////" + internalErrorCode)
    if (meetingStatus == MEETING_STATUS_IDLE || meetingStatus == MEETING_STATUS_FAILED) {
     // mZoomSDK = null
    }
  }

  override fun onZoomAuthIdentityExpired() {}
}