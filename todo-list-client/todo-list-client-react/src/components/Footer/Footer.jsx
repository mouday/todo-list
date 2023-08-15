import React, { Component } from 'react'
import './Footer.css'

export default class Footer extends Component {
  render() {
    const { list, onRemoveSeleted } = this.props

    const selectedCount = list.filter((item) => item.selected).length
    const totalCount = list.length

    return (
      <div className="footer">
        <div>
          已选中：{selectedCount}/{totalCount}
        </div>
        <div
          className={['footer__delete', selectedCount > 0 ? '': 'hide'].join(' ')}
          onClick={onRemoveSeleted}
        >
          批量移除
        </div>
      </div>
    )
  }
}
