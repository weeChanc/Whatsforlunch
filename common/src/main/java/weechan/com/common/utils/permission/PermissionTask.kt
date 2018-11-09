package weechan.com.common.utils.permission

import weechan.com.common.utils.permission.Permission
import weechan.com.common.utils.permission.PermissionGetCallBack

/**
 * Created by jimji on 2017/11/11.
 */
data class PermissionTask(val permission: Permission, val callBack: PermissionGetCallBack)