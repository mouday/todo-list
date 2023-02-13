import instance from './instance.js'

export async function getItemList() {
  const res = await instance.post('/getItemList', {})
  return res.data
}

/**
 * 
 * @param {*} data 
 * item_id
 * @returns 
 */
export async function getItemById(data) {
  const res = await instance.post('/getItemById', data)
  return res.data
}

export async function addItem(data) {
  const res = await instance.post('/addItem', data)
  return res
}

export async function updateItemById(data) {
  const res = await instance.post('/updateItemById', data)
  return res
}

/**
 * 
 * @param {*} data 
 * item_id
 * @returns 
 */
export async function deleteItemById(data) {
  const res = await instance.post('/deleteItemById', data)
  return res.data
}
