import React, { Component } from 'react'
import PropTypes from 'prop-types'

import './Footer.css'

export default class Footer extends Component {
  static propTypes = {
    list: PropTypes.array.isRequired,
    onRemoveSeleted: PropTypes.func.isRequired,
    onAllSeleted: PropTypes.func.isRequired,
  }

  handleSelectedChange = (e) => {
    const { onAllSeleted } = this.props
    onAllSeleted(e.target.checked)
  }

  render() {
    const { list, onRemoveSeleted } = this.props

    const selectedCount = list.filter((item) => item.selected).length
    const totalCount = list.length
    const checked = selectedCount === totalCount

    return (
      <div className="footer">
        <label className="item">
          <input
            type="checkbox"
            checked={checked}
            onChange={this.handleSelectedChange}
          />
        </label>
        <div>
          已选中：{selectedCount}/{totalCount}
        </div>
        <div
          className={['footer__delete', selectedCount > 0 ? '' : 'hide'].join(
            ' '
          )}
          onClick={onRemoveSeleted}
        >
          移除已完成
        </div>
      </div>
    )
  }
}
