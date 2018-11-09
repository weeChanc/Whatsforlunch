package weechan.com.common.utils

/**
 *
 * @author 214652773@qq.com
 * @user c
 * @create 2018/10/21 20:44
 *
 */


class Maps{
    class MapBuilder<V>{
        val map = hashMapOf<String,V>()
        operator fun String.minus(obj: V){
            map.put(this,obj)
        }
    }

    companion object {
        fun <V> buildMap( build : MapBuilder<V>.()->Unit): HashMap<String, V> {
            return MapBuilder<V>().apply(build).map
        }
    }
}


