package weechan.com.common.utils

/**
 *
 * @author 214652773@qq.com
 * @user c
 * @create 2018/10/21 20:44
 *
 */


class Maps{
    class MapBuilder{
        val map = hashMapOf<Any,Any>()
        operator fun String.minus(obj: Any){
            map.put(this,obj)
        }
    }

    companion object {
        fun buildMap( build : MapBuilder.()->Unit): HashMap<Any, Any> {
            return MapBuilder().apply(build).map
        }
    }
}


