package com.zoomtest.zoom.helper;

import us.zoom.sdk.InstantMeetingOptions;
import us.zoom.sdk.JoinMeetingOptions;
import us.zoom.sdk.MeetingOptions;
import us.zoom.sdk.MeetingViewsOptions;
import us.zoom.sdk.StartMeetingOptions;

public class ZoomMeetingUISettingHelper {

    private static final JoinMeetingOptions meetingOptions = new JoinMeetingOptions();
    public static boolean useExternalVideoSource = false;

    public static JoinMeetingOptions getMeetingOptions() {
        return meetingOptions;
    }

    public static StartMeetingOptions getStartMeetingOptions() {
        StartMeetingOptions opts = new StartMeetingOptions();
        fillMeetingOption(opts);
        return opts;
    }

    public static JoinMeetingOptions getJoinMeetingOptions() {
        JoinMeetingOptions opts = new JoinMeetingOptions();
        fillMeetingOption(opts);
        opts.no_audio = meetingOptions.no_audio;
        return opts;
    }

    private static MeetingOptions fillMeetingOption(MeetingOptions opts) {
        opts.no_driving_mode = true;
        opts.no_invite = true;
        opts.no_meeting_end_message = meetingOptions.no_meeting_end_message;
        opts.no_meeting_error_message = true;
        opts.no_titlebar = meetingOptions.no_titlebar;
        opts.no_bottom_toolbar = meetingOptions.no_bottom_toolbar;
        opts.no_dial_in_via_phone = true;
        opts.no_dial_out_to_phone = true;
        opts.no_disconnect_audio = true;
        opts.no_share = true;
        opts.no_video = true;
        opts.meeting_views_options = 45;
        opts.meeting_views_options = MeetingViewsOptions.NO_BUTTON_PARTICIPANTS;
        opts.invite_options = 255;
        opts.participant_id = meetingOptions.participant_id;
        opts.custom_meeting_id = meetingOptions.custom_meeting_id;
        opts.no_unmute_confirm_dialog = meetingOptions.no_unmute_confirm_dialog;
        opts.no_webinar_register_dialog = true;
        //opts.no_chat_msg_toast = true;
        return opts;
    }

    public static InstantMeetingOptions getInstantMeetingOptions() {
        InstantMeetingOptions opts = new InstantMeetingOptions();
        fillMeetingOption(opts);
        return opts;
    }
}
