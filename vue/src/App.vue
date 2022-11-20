<template>
  <el-config-provider :locale="locale">
    <router-view />
  </el-config-provider>

</template>


<script>
import { ElConfigProvider } from 'element-plus'

import zhCn from 'element-plus/lib/locale/lang/zh-cn'
// 测试前端init
export default {
  name: "App",
  components: {
    [ElConfigProvider.name]: ElConfigProvider,
  },
  data() {
    return {
      locale: zhCn,
    }
  },
}
</script>
