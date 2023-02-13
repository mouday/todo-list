import request from './request'
import { ItemModel } from '../models/ItemModel'
import { ListModel } from '../models/ListModel'

export async function getItemById(data: any) {
  return await request<ItemModel>({
    method: 'POST',
    url: '/getItemById',
    data: data,
  })
}

export async function getItemList(data: {
  status: number | undefined
  page: number | undefined
  size: number | undefined
}) {
  return await request<ListModel<ItemModel>>({
    method: 'POST',
    url: '/getItemList',
    data: data,
  })
}

export async function addItem(data: object) {
  return await request<ItemModel>({
    method: 'POST',
    url: '/addItem',
    data: data,
  })
}

export async function updateItemById(data: object) {
  return await request({
    method: 'POST',
    url: '/updateItemById',
    data: data,
  })
}

export async function deleteItemById(data: object) {
  return await request({
    method: 'POST',
    url: '/deleteItemById',
    data: data,
  })
}
