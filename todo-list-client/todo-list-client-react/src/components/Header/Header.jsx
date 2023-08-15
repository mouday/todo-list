import React, { Component } from 'react'
import PropTypes from 'prop-types'
import './Header.css'

export default class Header extends Component {
  // 类型限制
  static propTypes = {
    onAddItem: PropTypes.func.isRequired,
  }

  handleKeyUp = (e) => {
    const { target, key } = e

    const { onAddItem } = this.props

    if (key !== 'Enter') {
      return
    }

    if (!target.value.trim()) {
      return
    }

    // 获取输入框的值
    onAddItem(target.value.trim())
    target.value = ''
  }

  render() {
    return (
      <div className="header">
        <input
          type="text"
          className="mo-input header__input"
          placeholder="输入内容"
          onKeyUp={this.handleKeyUp}
        />
      </div>
    )
  }
}
