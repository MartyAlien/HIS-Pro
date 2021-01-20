import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '主页', icon: 'dashboard' }
    }]
  },
  {
    path: '/nursestation',
    component: Layout,
    redirect: '/nursestation/registered',
    name: 'Nursestation',
    meta: { title: '护士工作站', icon: 'el-icon-first-aid-kit' },
    children: [
      {
        path: 'registered',
        name: 'Registered',
        component: () => import('@/views/nursestation/registered/index'),
        meta: { title: '挂号', icon: 'table' }
      },
      {
        path: 'backno',
        name: 'Backno',
        component: () => import('@/views/nursestation/backno/index'),
        meta: { title: '退号', icon: 'el-icon-notebook-2' }
      },
      {
        path: 'charge',
        name: 'Charge',
        component: () => import('@/views/nursestation/charge/index'),
        meta: { title: '收费管理', icon: 'el-icon-notebook-2' }
      }
    ]
  },
  {
    path: '/doctorstation',
    component: Layout,
    redirect: '/doctorstation/medicalrecord',
    name: 'Doctorstation',
    meta: { title: '医生工作站', icon: 'el-icon-s-help' },
    children: [
      {
        path: 'medicalrecord',
        name: 'Medicalrecord',
        component: () => import('@/views/doctorstation/medicalrecord/index'),
        meta: { title: '门诊病例', icon: 'table' }
      },
      {
        path: 'recipe',
        name: 'Recipe',
        component: () => import('@/views/doctorstation/recipe/index'),
        meta: { title: '实时疫情', icon: 'el-icon-notebook-2' }
      }
    ]
  },
  {
    path: '/form',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Form',
        component: () => import('@/views/form/index'),
        meta: { title: 'Form', icon: 'form' }
      }
    ]
  },

  {
    path: '/nested',
    component: Layout,
    redirect: '/nested/menu1',
    name: 'Nested',
    meta: {
      title: 'Nested',
      icon: 'nested'
    },
    children: [
      {
        path: 'menu1',
        component: () => import('@/views/nested/menu1/index'), // Parent router-view
        name: 'Menu1',
        meta: { title: 'Menu1' },
        children: [
          {
            path: 'menu1-1',
            component: () => import('@/views/nested/menu1/menu1-1'),
            name: 'Menu1-1',
            meta: { title: 'Menu1-1' }
          },
          {
            path: 'menu1-2',
            component: () => import('@/views/nested/menu1/menu1-2'),
            name: 'Menu1-2',
            meta: { title: 'Menu1-2' },
            children: [
              {
                path: 'menu1-2-1',
                component: () => import('@/views/nested/menu1/menu1-2/menu1-2-1'),
                name: 'Menu1-2-1',
                meta: { title: 'Menu1-2-1' }
              },
              {
                path: 'menu1-2-2',
                component: () => import('@/views/nested/menu1/menu1-2/menu1-2-2'),
                name: 'Menu1-2-2',
                meta: { title: 'Menu1-2-2' }
              }
            ]
          },
          {
            path: 'menu1-3',
            component: () => import('@/views/nested/menu1/menu1-3'),
            name: 'Menu1-3',
            meta: { title: 'Menu1-3' }
          }
        ]
      },
      {
        path: 'https://panjiachen.github.io/vue-element-admin-site/#/',
        //component: () => import('@/views/nested/menu2/index'),
        name: 'Menu2',
        meta: { title: 'vue-api' }
      }
    ]
  },

  {
    path: 'external-link',
    component: Layout,
    children: [
      {
        //path: 'https://panjiachen.github.io/vue-element-admin-site/#/',
        path: 'https://github.com/MartyAlien',
        meta: { title: 'Go to github', icon: 'link' }
      }
    ]
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
